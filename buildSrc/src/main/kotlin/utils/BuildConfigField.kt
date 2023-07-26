import com.android.build.api.dsl.VariantDimension

fun VariantDimension.buildConfigField(name: String, value: String) =
    buildConfigField("String", name, "\"$value\"")

fun VariantDimension.buildConfigField(name: String, value: Int) =
    buildConfigField("int", name, value.toString())

fun VariantDimension.buildConfigField(name: String, value: List<String>) =
    buildConfigField(
        "String[]",
        name,
        value.joinToString(prefix = "{", postfix = "}") { "\"${it}\"" }
    )

