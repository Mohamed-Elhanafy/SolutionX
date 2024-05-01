package com.example.solutionx.common.presentaion

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <binding : ViewBinding> Fragment.viewBinding(viewBindingClass: Class<binding>): ReadOnlyProperty<Fragment, binding> {



    val inflateMethod = viewBindingClass.getMethod(
        "inflate",
        LayoutInflater::class.java,
        ViewGroup::class.java,
        Boolean::class.javaPrimitiveType
    ) // to create instance of the view binding class

    return object : ReadOnlyProperty<Fragment, binding> {
        private var binding: binding? = null

        override fun getValue(thisRef: Fragment, property: KProperty<*>): binding {
            binding?.let { return it }

            val lifecycle = thisRef.viewLifecycleOwner.lifecycle

            if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
                throw IllegalStateException("Should not attempt to get bindings when Fragment views are destroyed.")
            }

            @Suppress("UNCHECKED_CAST")
            val invoke = inflateMethod.invoke(null, layoutInflater, null, false) as binding
            return invoke.also { this.binding = it }
        }
    }
}

fun <Binding : ViewBinding> Activity.viewBinding(viewBindingClass: Class<Binding>): ReadOnlyProperty<Activity, Binding> {
    val inflateMethod = viewBindingClass.getMethod(
        "inflate",
        LayoutInflater::class.java,
        ViewGroup::class.java,
        Boolean::class.javaPrimitiveType
    )

    return object : ReadOnlyProperty<Activity, Binding> {
        private var binding: Binding? = null

        override fun getValue(thisRef: Activity, property: KProperty<*>): Binding {
            binding?.let { return it }

            @Suppress("UNCHECKED_CAST")
            val invoke = inflateMethod.invoke(null, thisRef.layoutInflater, null, false) as Binding
            return invoke.also { this.binding = it }
        }
    }
}