package it.wip.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.MotionEvent
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import it.wip.MainActivity
import it.wip.R
import it.wip.databinding.ActivityStoryStartedBinding
import it.wip.utils.fromShopElementNameToResource
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
        * blocco {} ogni volta che trascorre un secondo, le variabili first, second e
        * thirdAlreadyExecuted garantiscono che la funzione "backgroundEvolution" venga eseguita
        * una sola volta per slot temporale
        * */
        var firstAlreadyExecuted = true
        var secondAlreadyExecuted = true
        var thirdAlreadyExecuted = true




        //              EXTRAS
        val extras = intent.extras
        val avatar: String = extras?.get("selectedAvatar").toString()
        val floatStudyTime = extras?.get("studyTime").toString().toFloat()
        val floatBreakTime = extras?.get("breakTime").toString().toFloat()
        val studyTime = floatStudyTime.toLong()
        val breakTime = floatBreakTime.toLong()




        //              BINDING RESOURCES
        val stopButton = binding.stopButton
        val artwork = binding.canva
        val cronometro = binding.simpleChronometer
        val selectedAvatar = binding.companionImageView




        //              TIME MANAGEMENT
        /*
        * study time rappresenta il numero di minuti; lo moltiplichiamo per 60000 per tale unità di
        * misura in millisecondi dato che il cronometro lavora con i millisecondi
        * */
        val leftValueRange1 = ((floatStudyTime/4)*60000).toLong()
        val leftValueRange2 = leftValueRange1 + leftValueRange1
        val leftValueRange3 = leftValueRange2 + leftValueRange1

        //time range in cui evolvere l'opera d'arte
        var maxTime: Long = (studyTime+breakTime)*60000
        var timeRange1 = (leftValueRange1..leftValueRange2-40000)
        var timeRange2 = (leftValueRange2..leftValueRange3-40000)
        var timeRange3 = (leftValueRange3..(studyTime*60000)-40000)
        var pauseRange4 = (studyTime*60000..maxTime-40000)

        //slot temporali di prova; sostituire con quelli ricavati dallo slider
        //var maxTime = 40000
        //var timeRange1 = (5000..14000)
        //var timeRange2 = (15000..20000)
        //var timeRange3 = (21000..30000)




        //              BINDING RESOURCES METHODS AND RELATED CLASS METHODS
        selectedAvatar.setBackgroundResource(fromShopElementNameToResource(avatar))
        cronometro.start()
        cronometro.typeface = ResourcesCompat.getFont(this, R.font.press_start_2p)

        // invocazione metodo per scelta casuale del primo background tra quelli disponibili
        var selectedArtwork = backgroundSelector(artwork)

        //listener che gestisce cosa fare a schermo ogni volta che il tempo incrementa
        cronometro.setOnChronometerTickListener {
            val myTime = (SystemClock.elapsedRealtime() - cronometro.base)
            /*
            * if(myTime>=maxTime){...} è il blocco di codice che mette un nuovo quadro e lo fa
            * evolvere quando un quadro viene completamente dipinto
            * */
            if(myTime>=maxTime){
                selectedAvatar.setBackgroundResource(fromShopElementNameToResource(avatar))
                firstAlreadyExecuted = true
                secondAlreadyExecuted = true
                thirdAlreadyExecuted = true
                timeRange1 = (maxTime+leftValueRange1..maxTime+leftValueRange2-40000)
                timeRange2 = (maxTime+leftValueRange2..maxTime+leftValueRange3-40000)
                timeRange3 = (maxTime+leftValueRange3..maxTime+(studyTime*60000)-40000)
                pauseRange4 = ((studyTime*60000)+maxTime..maxTime+maxTime-40000)
                maxTime+=maxTime

                // porzione di codice che evita di farmi vedere due volte di fila lo stesso quadro
                var newSelectedArtwork = backgroundSelector(artwork)
                while(selectedArtwork == newSelectedArtwork){
                    newSelectedArtwork = backgroundSelector(artwork)
                }
                selectedArtwork = newSelectedArtwork
            }

            /*
            * when (myTime){...} è il blocco di codice che mi evolve il quadro a seconda dello slot
            * temporale in cui mi trovo
            * */
            when (myTime){
                in timeRange1 -> {
                    if(firstAlreadyExecuted) {
                        backgroundEvolution(artwork, selectedArtwork, 2)
                        firstAlreadyExecuted = false
                    }
                }
                in timeRange2 -> {
                    if(secondAlreadyExecuted) {
                        backgroundEvolution(artwork, selectedArtwork, 3)
                        secondAlreadyExecuted = false
                    }
                }
                in timeRange3 -> {
                    if(thirdAlreadyExecuted) {
                        backgroundEvolution(artwork, selectedArtwork, 4)
                        thirdAlreadyExecuted = false
                    }
                }
                in pauseRange4 -> {
                    selectedAvatar.setBackgroundResource(R.drawable.bonfire)
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
            startActivity(Intent(this, MainActivity::class.java))
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