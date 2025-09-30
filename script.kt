package com.ricelab.peptalk

object ScriptManager {

    private fun isFemale(sex: String): Boolean {
        return sex.equals("female", ignoreCase = true)
    }

    fun getIntroScript(name: String, sex: String, lang: String): String {
        return if (lang.equals("en", true)) {
            "Hi $name, thank you for coming to visit me today. " +
                    "I've placed some objects on the table. " +
                    "You can touch them and ask me anything you'd like. " +
                    "Later, I'll tell you what to do with one of them. " +
                    "But first, is there one that catches your eye?"
        } else {
            val visit = if (isFemale(sex)) "venuta" else "venuto"
            "Ciao $name, grazie per essere $visit a trovarmi oggi. " +
                    "Ho messo sul tavolo qualche oggetto. " +
                    "Se vuoi puoi toccarli e puoi chiedermi quello che vuoi. " +
                    "Poi, quando sarà il momento ti dirò cosa fare con uno di questi. " +
                    "Ma prima, dimmi ce n’è uno in particolare che ti attira di più?"
        }
    }

    fun getStartScript(lang: String): String {
        return if (lang.equals("en", true)) {
            "Now it's time to use one of the objects on the table!"
        } else {
            "Ora è il momento di usare uno degli oggetti sul tavolo!"
        }
    }

    fun getAssignTask(sex: String, lang: String): Pair<String, String> {
        return if (lang.equals("en", true)) {
            assignTaskScriptsEn.random()
        } else {
            val ready = if (isFemale(sex)) "pronta" else "pronto"
            assignTaskScriptsIt.map { (task, script) ->
                task to script.replace("{ready}", ready)
            }.random() //.first { it.first == "beads" } //.random ()
        }
    }

    fun getBeads(sex: String, lang: String): String {
        return if (lang == "en") beadsScriptEn else beadsScriptIt.replace("{ready}", if (isFemale(sex)) "pronta" else "pronto")
    }

    fun getMemory(sex: String, lang: String): String {
        return if (lang == "en") memoryScriptEn else memoryScriptIt.replace("{ready}", if (isFemale(sex)) "pronta" else "pronto")
    }

    fun getMandala(sex: String, lang: String): String {
        return if (lang == "en") mandalaScriptEn else mandalaScriptIt.replace("{ready}", if (isFemale(sex)) "pronta" else "pronto")
    }

    fun preJack(lang: String): String {
        return if (lang == "en") preJackEn else preJackIt
    }

    fun getJack(sex: String, lang: String): String {
        return if (lang == "en") jackScriptEn else jackScriptIt.replace("{ready}", if (isFemale(sex)) "pronta" else "pronto")
    }

    fun preJump(lang: String): String {
        return if (lang == "en") preJumpEn else preJumpIt
    }

    fun getJump(sex: String, lang: String): String {
        return if (lang == "en") jumpScriptEn else jumpScriptIt.replace("{ready}", if (isFemale(sex)) "pronta" else "pronto")
    }

    fun getStartTaskScript(lang: String): String {
        return if (lang == "en") "You can start now. Go!" else "Ora puoi partire. Via!"
    }

    fun getStopScript(lang: String): String {
        return if (lang == "en") "Time is up. How was it?" else "Ora il tempo è finito. Come è andata?"
    }

    fun getFarewellScript(lang: String): String {
        return if (lang == "en") "It's time to say goodbye. I hope to see you soon. Bye!" else "Ora dobbiamo salutarci. Spero di rivederti presto. Ciao!"
    }

    fun getProactivePrompt(lang: String): String {
        return if (lang == "en") checkUserEn.random() else checkUserIt.random()
    }

    // === PROMPTS ===

    private val checkUserIt = listOf(
        "Come va?", "Come sta andando?", "Tutto bene?", "Spero che stia andando tutto bene"
    )

    private val checkUserEn = listOf(
        "How’s it going?", "Are you doing okay?", "Everything alright?", "I hope you're doing well"
    )

    // === TASK ASSIGNMENT ===

    private val assignTaskScriptsIt = listOf(
        "beads" to """
            L’oggetto che dovrai usare sarà il set di perline colorate e le pinzette.
            Ora, posiziona le perline, le pinzette e il contenitore trasparente all’interno del quadrato rosso sul tavolo.
            Quando sei {ready}, schiaccia la freccia avanti e ti spiegherò le regole. Altrimenti, schiaccia la freccia ripeti.
        """.trimIndent(),

        "jacks" to """
            L’oggetto che dovrai usare sarà il jack’s game ovvero il gioco sul tavolo con una pallina rossa e le stelline di metallo.
            Ora, posiziona la pallina e le stelline all’interno del quadrato rosso sul tavolo.
            Quando sei {ready}, schiaccia la freccia avanti e ti spiegherò le regole. Altrimenti, schiaccia la freccia ripeti.
        """.trimIndent(),

        "memory" to """
            L’oggetto che dovrai usare sarà il memory.
            Ora, prendi le carte del memory, mescolale e posizionale a faccia in giù all’interno del quadrato rosso sul tavolo.
            Quando sei {ready}, schiaccia la freccia avanti e ti spiegherò le regole. Altrimenti, schiaccia la freccia ripeti.
        """.trimIndent(),

        "jump" to """
            L’oggetto che dovrai usare sarà l’elastico da palestra.
            L’obiettivo del gioco sarà fare tre serie da 15 giamping jack.
            Dovrai tendere l’elastico con le mani ogni volta che apri gambe e piedi. 
            Per ogni serie, potrai riposarti o continuare, ma devi finire entro 5 minuti.
            Ti faccio vedere come si fa.
        """.trimIndent(),

        "mandala" to """
            Gli oggetti che dovrai usare saranno i disegni mandala e i pennarelli colorati.
            Ora, prendi i disegni e i pennarelli e posizionali all’interno del quadrato rosso sul tavolo.
            Quando sei {ready}, schiaccia la freccia avanti e ti spiegherò le regole. Altrimenti, schiaccia la freccia ripeti.
        """.trimIndent()
    )

    private val assignTaskScriptsEn = listOf(
        "beads" to """
            You'll use the set of colorful beads and tweezers.
            Place them and the clear container in the red square on the table.
            When you're ready, press the forward arrow to hear the rules. Otherwise, press repeat.
        """.trimIndent(),

        "jacks" to """
            You'll use the jacks game with the red ball and metal stars.
            Put them in the red square on the table.
            When you're ready, press the forward arrow to hear the rules. Otherwise, press repeat.
        """.trimIndent(),

        "memory" to """
            You’ll play memory game.
            Shuffle the cards and place them face down in the red square.
            When you're ready, press the forward arrow to hear the rules. Otherwise, press repeat.
        """.trimIndent(),

        "jump" to """
            You’ll use the exercise band.
            The goal is to do 3 sets of 15 jumping jacks.
            Pull the band apart when your legs open.
            You can rest between sets, but finish within 5 minutes.
            I'll show you how to do it.
        """.trimIndent(),

        "mandala" to """
            You’ll use the mandala drawings and color markers.
            Place them in the red square.
            When you're ready, press the forward arrow to hear the rules. Otherwise, press repeat.
        """.trimIndent()
    )

    // === TASK SPECIFICS ===

    private val beadsScriptIt = """
        Il tuo obiettivo sarà dividere le perline per colore usando le pinzette.
        Le perline vanno nel contenitore trasparente, con ogni colore in una sezione diversa.
        Hai 5 minuti per completare il task. Quando sei {ready}, schiaccia la freccia avanti per iniziare. Altrimenti, schiaccia la freccia ripeti.
    """.trimIndent()

    private val beadsScriptEn = """
        Your goal is to sort the beads by color using the tweezers.
        Put each color into a different section of the transparent container.
        You have 5 minutes to complete the task. When you're ready, press the forward arrow to start. Otherwise, press repeat.
    """.trimIndent()

    private val memoryScriptIt = """
        Il gioco consiste nel trovare le coppie di carte uguali nel minor tempo possibile.
        Girane due per volta se è una coppia mettile da parte e poi continua, se sono diverse, ricopri quelle che hai girato e ricomincia. 
        Hai 5 minuti. Quando sei {ready}, schiaccia la freccia avanti per iniziare. Altrimenti, schiaccia la freccia ripeti.
    """.trimIndent()

    private val memoryScriptEn = """
        Find matching pairs of cards by turning 2 at a time.
        If you find a couple put them aside. If they are different turn them back and start again.
        Complete it within 5 minutes. When you're ready, press the forward arrow to start. Otherwise, press repeat.
    """.trimIndent()

    private val mandalaScriptIt = """
        Dovrai colorare un disegno mandala usando i pennarelli, prendendo ispirazione da quello già colorato.
        Hai 5 minuti per completare il disegno. Quando sei {ready}, schiaccia la freccia avanti per iniziare. Altrimenti, schiaccia la freccia ripeti.
    """.trimIndent()

    private val mandalaScriptEn = """
        You’ll color a mandala using markers taking inspiration from the one already coloured.
        You have 5 minutes to complete it. When you're ready, press the forward arrow to start. Otherwise, press repeat.
    """.trimIndent()

    private val preJackIt = """
        Il gioco consiste nel raccogliere tutte le stelline dal tavolo usando una sola mano mentre lanci la pallina.
        Fai rimbalzare la pallina e raccogli una stellina con la stessa mano prima che la palla rimbalzi due volte.
        Ti faccio vedere come si fa.
    """.trimIndent()

    private val preJackEn = """
        The goal is to collect all the stars using one hand while bouncing the ball.
        Catch one star before the ball bounces twice. I’ll show you how.
    """.trimIndent()

    private val jackScriptIt = """
        Per finire il gioco, raccogli tutte le stelline con una mano.
        Se la palla rimbalza più di una volta prima di raccoglierne una, ricomincia.
        Hai 5 minuti. Quando ti senti {ready}, premi la freccia avanti per iniziare. Per riascoltare, premi la freccia ripeti.
    """.trimIndent()

    private val jackScriptEn = """
        Finish the game by collecting all the stars with one hand.
        If the ball bounces more than once, restart. You have 5 minutes.
        When you're ready, press the forward arrow to start. Otherwise, press repeat.
    """.trimIndent()

    private val preJumpIt = "Se vuoi riascoltare le regole e guardare il video, premi la freccia ripeti. Altrimenti, premi la freccia avanti per le prossime istruzioni."
    private val preJumpEn = "If you want to rewatch the video and hear the instructions again, press repeat. Otherwise, press the forward arrow to continue."

    private val jumpScriptIt = "Ora, prendi l’elastico e posizionati sulla X rossa sul pavimento. Quando sei {ready}, schiaccia la freccia avanti per iniziare."
    private val jumpScriptEn = "Now grab the band and stand on the red X on the floor. When you're ready, press the forward arrow to start."
}