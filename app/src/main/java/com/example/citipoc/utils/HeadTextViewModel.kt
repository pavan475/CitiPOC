package com.example.citipoc.utils

class HeadTextViewModel : BaseViewModel {
    var text: String
    var textBoldFlag = 0

    constructor(text: String, textBoldFlag: Int) : super(PnOBaseViewModel.Types.HEAD_TEXT_MODEL) {
        this.text = text
        this.textBoldFlag = textBoldFlag
    }

    constructor(itemType: Int, text: String) : super(itemType) {
        this.text = text
    }
}
