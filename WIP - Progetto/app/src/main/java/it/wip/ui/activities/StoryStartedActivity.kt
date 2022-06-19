package it.wip.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.SystemClock
import android.view.MotionEvent
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import it.wip.R
import it.wip.databinding.ActivityStoryStartedBinding
import it.wip.ui.dialogs.DialogCoins
import it.wip.utils.*
import it.wip.viewModel.StoryStartedViewModel

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



        //              GUARDS
        /*
        * in riferimento al listener "setOnChronometerTickListener", che esegue il contenuto del
        * blocco {} ogni volta che trascorre un secondo, le variabili first, second, third e
        * fourthAlreadyExecuted garantiscono che la funzione "backgroundEvolution" venga eseguita
        * una sola volta per slot temporale
        * */
        var firstAlreadyExecuted = true
        var secondAlreadyExecuted = true
        var thirdAlreadyExecuted = true
        var fourthAlreadyExecuted = true




        //              EXTRAS
        val extras = intent.extras
        val avatar: String = extras?.get("selectedAvatar").toString()
        val floatStudyTime = extras?.get("studyTime").toString().toFloat()
        val floatBreakTime = extras?.get("breakTime").toString().toFloat()




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

        var leftValueRange1 = step1
        var leftValueRange2 = step2
        var leftValueRange3 = step3
        var pauseMoment = studyTime
        var actualTime: Long = 0

        //time range in cui evolvere l'opera d'arte
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
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF)
        var currentState = "study"
        var mReceiver = ScreenStateReceiver(quote, currentState)
        registerReceiver(mReceiver, intentFilter)




        //              BINDING RESOURCES METHODS AND RELATED CLASS METHODS
        selectedAvatar.setBackgroundResource(fromShopElementNameToResource(avatar))
        cronometro.start()
        cronometro.typeface = ResourcesCompat.getFont(this, R.font.press_start_2p)

        // invocazione metodo per scelta casuale del primo background tra quelli disponibili
        var selectedArtwork = backgroundSelector(artwork)

        //listener che gestisce cosa fare a schermo ogni volta che il tempo incrementa
        cronometro.setOnChronometerTickListener {
            val myTime = (SystemClock.elapsedRealtime() - cronometro.base)
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
                firstAlreadyExecuted = true
                secondAlreadyExecuted = true
                thirdAlreadyExecuted = true
                fourthAlreadyExecuted = true
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
                if(fourthAlreadyExecuted) {
                    backgroundEvolution(artwork, selectedArtwork, 4)
                    fourthAlreadyExecuted = false
                    selectedAvatar.setBackgroundResource(R.drawable.bonfire)

                    // setto il receiver in modo che mostri quotes relative alla pausa
                    unregisterReceiver(mReceiver)
                    currentState = "pause"
                    mReceiver = ScreenStateReceiver(quote, currentState)
                    registerReceiver(mReceiver, intentFilter)
                    quote.setText(firstPauseQuote)
                }
            }else if(myTime>=leftValueRange3){
                if(thirdAlreadyExecuted) {
                    backgroundEvolution(artwork, selectedArtwork, 4)
                    thirdAlreadyExecuted = false
                }
            }else if(myTime>=leftValueRange2){
                if(secondAlreadyExecuted) {
                    backgroundEvolution(artwork, selectedArtwork, 3)
                    secondAlreadyExecuted = false
                }
            } else if(myTime>=leftValueRange1){
                if(firstAlreadyExecuted) {
                    backgroundEvolution(artwork, selectedArtwork, 2)
                    firstAlreadyExecuted = false
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

        stopButton.setOnClickListener {
            cronometro.stop()
            val coinsReceived = viewModel.coinCalculator(studyTime, breakTime, actualTime)
            val dialogCoin = DialogCoins(coinsReceived)
            dialogCoin.show(supportFragmentManager, "coinInfo")
        }
    }




    //              SCELTA DINAMICA DEL QUADRO
    private fun backgroundSelector (artwork: ImageView): Int{
        val backgrounds = viewModel.backgroundShoppedElements
        var selectedArtwork = 1

        //il range è 1..3 perchè all'inizio abbiamo solo 3 quadri sbloccati
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
