
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Test(var initialValue: Int) {
    operator fun setValue(thisRef: Any?, property:KProperty<*>, newValue:Int) {
        this.initialValue = newValue
    }
    operator fun getValue(thisRef: Any?, property: KProperty<*>) = this.initialValue
}
var test by Test(1)

fun main(args: Array<String>) {
    println("myEven:$test")
}