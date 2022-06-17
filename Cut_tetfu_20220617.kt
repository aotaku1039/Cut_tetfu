class CutTetfu { //テト譜カットのクラス
    var input_tetfu = "" //入力テト譜
    var output_type = 0  //出力形式

    fun inputTetfu(): String { //テト譜入力
        while (true) {
            input_tetfu = readLine()!!
            if (input_tetfu.indexOf("115@") != -1) { //「115@」を検知するまでは入力を継続させる
                return input_tetfu
            } else {
                println("URLには「115@」が含まれている必要があります")
                println("The URL must contain \"115@\".")
                println("")
            }
        }
    }

    fun formatStrInt(text: String): Int { //文字を整数に変換
        var num = 0

        try {
            num = text.toInt()
        } catch (e: NumberFormatException) {
            num = -1
        } finally {
            return num
        }
    }

    fun inputType(): Int { //出力形式入力
        var input_text = ""
        var input_num = 0

        while (true) {
            input_text = readLine()!!
            input_num  = formatStrInt(input_text)

            if (0 <= input_num && input_num <= 8) { //0以上8以下の数値が打ち込まれていたときだけ通す
                return input_num
            } else {
                println("0~8の範囲で整数を入力してください")
                println("Enter an integer in the range 0~8.")
                println("")
            }
        }
    }

    fun cutTetfuText(tetfu: String): String { //テト譜カット
        val START = tetfu.indexOf("@")
        var new_tetfu = tetfu //仮引数はval宣言と同等、つまり書き換え不可能なので　書き換え可能なvar宣言変数に値渡しする

        new_tetfu = new_tetfu.substring(START + 1) //最初の不要部分を取り除く

        new_tetfu = new_tetfu.replace("?", "") //「?」を取り除く
        new_tetfu = new_tetfu.replace(" ", "") //空白を取り除く
        new_tetfu = new_tetfu.replace(Regex("\n"), "") //改行を取り除く

        return new_tetfu
    }

    fun plusOutputFormat(type: Int, text: String): String {
        var plustext: String = ""

        when (type) {
            1 -> {plustext = "https://fumen.zui.jp/?v115@"} //1...日本版のEdit形式
            2 -> {plustext = "https://fumen.zui.jp/?d115@"} //2...日本版のFullList形式
            3 -> {plustext = "https://fumen.zui.jp/?D115@"} //3...日本版のMiniList形式
            4 -> {plustext = "https://fumen.zui.jp/?m115@"} //4...日本版のView形式
            5 -> {plustext = "https://knewjade.github.io/fumen-for-mobile/#?d=v115@"} //5...モバイル版
            6 -> {plustext = "https://harddrop.com/fumen/?v115@"} //6...HardDrop版のEdit形式
            7 -> {plustext = "https://harddrop.com/fumen/?d115@"} //7...HardDrop版のList形式
            8 -> {plustext = "https://harddrop.com/fumen/?m115@"} //8...HardDrop版のView形式
            else -> {plustext = "v115@"} //0...RAW DATA(v115@スタート)
        }

        return plustext + text
    }
}

fun cls(): Unit { //仮のコンソール画面消去
    for (i in 1..100){
        println("")
    }
}

fun main() {
    val cut_tetfu = CutTetfu()

    cls()

    println("テト譜URLを貼り付けて、Enterキーを押してください")
    println("Paste the tetfu URL and press Enter.")
    println("")
    cut_tetfu.input_tetfu = cut_tetfu.inputTetfu()

    println("")
    println("")
    println("出力形式を0~8の数値で入力し、Enterキーを押してください")
    println("Enter the output format as a number from 0 to 8 and press the Enter key.")
    println("")
    println("0...RAW DATA(v115@スタート)")
    println("1...日本版のEdit形式(https://fumen.zui.jp/?v115@スタート)")
    println("2...日本版のFullList形式(https://fumen.zui.jp/?d115@スタート)")
    println("3...日本版のMiniList形式(https://fumen.zui.jp/?D115@スタート)")
    println("4...日本版のView形式(https://fumen.zui.jp/?m115@スタート)")
    println("5...モバイル版(https://knewjade.github.io/fumen-for-mobile/#?d=v115@スタート)")
    println("6...HardDrop版のEdit形式(https://harddrop.com/fumen/?v115@スタート)")
    println("7...HardDrop版のList形式(https://harddrop.com/fumen/?d115@スタート)")
    println("8...HardDrop版のView形式(https://harddrop.com/fumen/?m115@スタート)")
    println("")
    cut_tetfu.output_type = cut_tetfu.inputType()


    val before_length = cut_tetfu.input_tetfu.length //短縮前の文字数

    cut_tetfu.input_tetfu = cut_tetfu.cutTetfuText(cut_tetfu.input_tetfu) //短縮する

    val after_length =  cut_tetfu.input_tetfu.length //短縮語の文字数


    val result_URL = cut_tetfu.plusOutputFormat(cut_tetfu.output_type, cut_tetfu.input_tetfu)

    cls()


    println("結果URL  result URL  ${before_length}文字 → ${after_length}文字")
    println("")
    println(result_URL)
    println("")

    println("URLをドラッグして選択し、Ctrl+Cキーでコピーしてください")
    println("Drag the URL to select it and press Ctrl + C to copy it.")
    println("")
    println("コピーできたら、Enterキーを押してプログラムを終了させてください")
    println("After copying, press Enter to exit the program.")
    val tmp = readLine()!!
    
}