package com.gcgenome.lims.test

data class Guardant (
    val code: String,
    val name: String,
): HasCode, HasName, HasCategory {
    override fun code() = code
    override fun name() = name
    override fun category() = HasCategory.Category.ETC
    companion object {
        private val A003 = Guardant(code="A003", name="가던트 액체생검 (G360)")
        private val A004 = Guardant(code="A004", name="가던트 액체생검 (G360) 재검사")
        private val A026 = Guardant(code="A026", name="가던트 리빌(Reveal)")
        private val A027 = Guardant(code="A027", name="가던트 리빌 번들(Reveal Bundle)")
        private val A028 = Guardant(code="A028", name="가던트 조직생검(Tissue Next)")
        private val A029 = Guardant(code="A029", name="가던트 조직생검(Tissue Next) 재검사")
        private val A030 = Guardant(code="A030", name="가던트360 TMB/MSI (LDT 2.12)")
        private val A031 = Guardant(code="A031", name="가던트360 TMB/MSI 재검사(LDT 2.12)")
        fun values() = listOf(
            A003, A004, A026, A027, A028, A029, A030, A031
        )
    }
}