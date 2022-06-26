package it.wip.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.SystemClock
import android.view.MotionEvent
import android.widget.Chronometer
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import it.wip.R
import it.wip.databinding.ActivityStoryStartedBinding
import it.wip.ui.dialogs.DialogCoins
import it.wip.ui.dialogs.DialogHardcoreMode
import it.wip.utils.*
import it.wip.viewModel.StoryStartedViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class StoryStartedActivity : AppCompatActivity(){

    private lateinit var viewModel: StoryStartedViewModel


    @SuppressLint("ClickableViewAccessibility", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityStoryStartedBinding = DataBindingUtil.setContentView(this, R.layout.activity_story_started)
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[StoryStartedViewModel::class.java]

        binding.lifecycleOwner = this
        binding.viewModel = viewModel




        //              EXTRAS
        val extras = intent.extras
        val newStoryName = extras?.get("newStoryName").toString()
        val avatar: String = extras?.get("selectedAvatar").toString()
        val floatStudyTime = extras?.get("studyTime") as Float
        val floatBreakTime = extras?.get("breakTime") as Float




        //              TIME MANAGEMENT
        /*
        * study time rappresenta il tempo di studio espresso in minuti; lo moltiplichiamo per 60000
        * al fine di tradurre tale misura in millisecondi dato che il cronometro lavora con i
        * millisecondi; analogamente accade con le altre variabili espresse in questa sezione.
        * */
        val studyTime = ((floatStudyTime.toLong())*60000)
        val breakTime = ((floatBreakTime.toLong())*60000)
        val step1 = ((floatStudyTime/4)*60000).toLong()
        val step2 = step1 + step1
        val step3 = step2 + step1

        // i time range ci dicono dopo quanto tempo far evolvere un quadro
        var leftValueRange1 = step1
        var leftValueRange2 = step2
        var leftValueRange3 = step3
        var pauseMoment = studyTime
        var actualTime: Long = 0

        //time range totale in cui evolvere l'opera d'arte
        var maxTime: Long = studyTime+breakTime




        //              BINDING RESOURCES
        val stopButton = binding.stopButton
        val artwork = binding.canva
        val cronometro = binding.simpleChronometer
        val selectedAvatar = binding.companionImageView
        val quote = binding.messagesTextView




        //              DEVICE WAKE-UP
        // Scelta della prima quote che dice l'avatar
        val firstEncouragementQuote = encouragementQuotes().random()
        val firstPauseQuote = pauseQuotes().random()
        quote.setText(firstEncouragementQuote)

        // Cambio quotes dell'avatar quando sblocchi il telefono
        val intentFilter = IntentFilter(Intent.ACTION_SCREEN_ON)
        var currentState = "study"
        var mReceiver = ScreenStateReceiver(quote, currentState)
        registerReceiver(mReceiver, intentFilter)



        //              DEVICE SCREEN OFF
        registerReceiver(viewModel.screenOffDetector, viewModel.pauseIntentFilter)




        //              BINDING RESOURCES METHODS AND RELATED CLASS METHODS
        selectedAvatar.setBackgroundResource(fromShopElementNameToResource(avatar))
        cronometro.start()
        cronometro.typeface = ResourcesCompat.getFont(this, R.font.press_start_2p)

        // invocazione metodo per scelta casuale del primo background tra quelli disponibili
        var selectedArtwork = backgroundSelector(artwork)


        var myTime: Long = 0
        //listener che gestisce cosa fare a schermo ogni volta che il tempo incrementa
        cronometro.setOnChronometerTickListener {
            myTime = (SystemClock.elapsedRealtime() - cronometro.base)
            actualTime = myTime
            /*
            * if(myTime>=maxTime){...} è il blocco di codice che mette un nuovo quadro e lo fa
            * evolvere quando un quadro viene completamente dipinto.
            *
            * gli altri esle if(..){}  costituiscono il blocco di codice che mi evolve il quadro a seconda dello slot
            * temporale in cui mi trovo
            * */
            if(myTime>=maxTime){
                selectedAvatar.setBackgroundResource(fromShopElementNameToResource(avatar))
                viewModel.firstAlreadyExecuted = true
                viewModel.secondAlreadyExecuted = true
                viewModel.thirdAlreadyExecuted = true
                viewModel.fourthAlreadyExecuted = true
                leftValueRange1 = maxTime+step1
                leftValueRange2 = maxTime+step2
                leftValueRange3 = maxTime+step3
                pauseMoment = maxTime+studyTime
                maxTime+=maxTime

                // setto il receiver in modo che mostri quotes di incoraggiamento
                unregisterReceiver(mReceiver)
                currentState = "study"
                mReceiver = ScreenStateReceiver(quote, currentState)
                registerReceiver(mReceiver, intentFilter)
                quote.setText(firstEncouragementQuote)

                // porzione di codice che evita di farmi vedere due volte di fila lo stesso quadro
                var newSelectedArtwork = backgroundSelector(artwork)
                while(selectedArtwork == newSelectedArtwork){
                    newSelectedArtwork = backgroundSelector(artwork)
                }
                selectedArtwork = newSelectedArtwork
            }else if(myTime>=pauseMoment){
                if(viewModel.fourthAlreadyExecuted) {
                    backgroundEvolution(artwork, selectedArtwork, 4)
                    viewModel.fourthAlreadyExecuted = false
                    selectedAvatar.setBackgroundResource(R.drawable.bonfire)

                    // setto il receiver in modo che mostri quotes relative alla pausa
                    unregisterReceiver(mReceiver)
                    currentState = "pause"
                    mReceiver = ScreenStateReceiver(quote, currentState)
                    registerReceiver(mReceiver, intentFilter)
                    quote.setText(firstPauseQuote)
                }
            }else if(myTime>=leftValueRange3){
                if(viewModel.thirdAlreadyExecuted) {
                    backgroundEvolution(artwork, selectedArtwork, 4)
                    viewModel.thirdAlreadyExecuted = false
                }
            }else if(myTime>=leftValueRange2){
                if(viewModel.secondAlreadyExecuted) {
                    backgroundEvolution(artwork, selectedArtwork, 3)
                    viewModel.secondAlreadyExecuted = false
                }
            } else if(myTime>=leftValueRange1){
                if(viewModel.firstAlreadyExecuted) {
                    backgroundEvolution(artwork, selectedArtwork, 2)
                    viewModel.firstAlreadyExecuted = false
                }
            }
        }

        stopButton.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> stopButton.setImageResource(R.drawable.stop_button_pressed)
                MotionEvent.ACTION_UP -> stopButton.setImageResource(R.drawable.stop_button)
            }
            v?.onTouchEvent(event) ?: true
        }

        //actions executed when stop button is clicked
        stopButton.setOnClickListener {
            cronometro.stop()
            unregisterReceiver(viewModel.screenOffDetector)
            unregisterReceiver(mReceiver)
            val coinsReceived = viewModel.coinCalculator(studyTime, breakTime, actualTime)
            val dialogCoin = DialogCoins(coinsReceived)
            dialogCoin.show(supportFragmentManager, "coinInfo")
            //after checking a minum time of 30 seconds, the story will be add to the Kingdom
            if(myTime>=10000) {
                //CALL METHOD TO INSERT A NEW STORY
                lifecycleScope.launch { viewModel.addNewStory(newStoryName,
                    cronometro.text.toString(), avatar
                ) }
            }
        }
    }

    override fun onRestart() {
        super.onRestart()

        val extras = intent.extras
        val selectedMode = extras?.get("mode").toString().toInt()

        if(selectedMode==1){
            if(viewModel.flag1){
                viewModel.flag1 = false
            }else{
                viewModel.flag2 = true
            }
        }
    }

    override fun onResume() {

        super.onResume()

        val extras = intent.extras
        val selectedMode = extras?.get("mode").toString().toInt()

        val stopChronometer = findViewById<Chronometer>(R.id.simpleChronometer)

        if(selectedMode==1){
            if(viewModel.flag2){
                viewModel.flag2 = false
                stopChronometer.stop()
                unregisterReceiver(viewModel.screenOffDetector)
                val dialogHardcoreMode = DialogHardcoreMode()
                dialogHardcoreMode.show(supportFragmentManager, "dialogHardcoreMode")
            }
        }
    }




    //              SCELTA DINAMICA DEL QUADRO
    private fun backgroundSelector (artwork: ImageView): Int{
        val backgrounds = viewModel.backgroundShoppedElements
        var selectedArtwork = 1

        when (backgrounds.random()) {
            "the_persistence_of_memory" -> {
                artwork.setBackgroundResource(R.drawable.dali_1)
                selectedArtwork = 1
            }
            "hopper_nighthawks" -> {
                artwork.setBackgroundResource(R.drawable.hopper_1)
                selectedArtwork = 2
            }
            "the_scream" -> {
                artwork.setBackgroundResource(R.drawable.munch_bg_1)
                selectedArtwork = 3
            }
            "creation_of_adam" -> {
                artwork.setBackgroundResource(R.drawable.adam_1)
                selectedArtwork = 4
            }
            "lovers" -> {
                artwork.setBackgroundResource(R.drawable.magritte_kiss_1)
                selectedArtwork = 5
            }
            "weathfield_with_crows" -> {
                artwork.setBackgroundResource(R.drawable.field_with_crows_1)
                selectedArtwork = 6
            }
        }
        return selectedArtwork
    }




    //              AUTO-AGGIORNAMENTO DIPINTO MENTRE SCORRE IL TIMER
    private fun backgroundEvolution(artwork: ImageView, artworkId: Int, artworkCurrentState:Int){

        when (artworkId) {
            1 -> {
                when (artworkCurrentState) {
                    2 -> artwork.setBackgroundResource(R.drawable.dali_2)
                    3 -> artwork.setBackgroundResource(R.drawable.dali_3)
                    4 -> artwork.setBackgroundResource(R.drawable.dali_4)
                }
            }
            2 -> {
                when (artworkCurrentState) {
                    2 -> artwork.setBackgroundResource(R.drawable.hopper_2)
                    3 -> artwork.setBackgroundResource(R.drawable.hopper_3)
                    4 -> artwork.setBackgroundResource(R.drawable.hopper_4)
                }
            }
            3 -> {
                when (artworkCurrentState) {
                    2 -> artwork.setBackgroundResource(R.drawable.munch_bg_2)
                    3 -> artwork.setBackgroundResource(R.drawable.munch_bg_3)
                    4 -> artwork.setBackgroundResource(R.drawable.munch_bg_4)
                }
            }
            4 -> {
                when (artworkCurrentState) {
                    2 -> artwork.setBackgroundResource(R.drawable.adam_2)
                    3 -> artwork.setBackgroundResource(R.drawable.adam_3)
                    4 -> artwork.setBackgroundResource(R.drawable.adam_4)
                }
            }
            5 -> {
                when (artworkCurrentState) {
                    2 -> artwork.setBackgroundResource(R.drawable.magritte_kiss_2)
                    3 -> artwork.setBackgroundResource(R.drawable.magritte_kiss_3)
                    4 -> artwork.setBackgroundResource(R.drawable.magritte_kiss_4)
                }
            }
            6 -> {
                when (artworkCurrentState) {
                    2 -> artwork.setBackgroundResource(R.drawable.field_with_crows_2)
                    3 -> artwork.setBackgroundResource(R.drawable.field_with_crows_3)
                    4 -> artwork.setBackgroundResource(R.drawable.field_with_crows_4)
                }
            }
        }
    }
}
