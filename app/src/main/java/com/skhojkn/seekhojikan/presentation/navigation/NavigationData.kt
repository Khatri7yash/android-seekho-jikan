package com.skhojkn.seekhojikan.presentation.navigation

data class NavigationData(
    val navigation: Screen?,
    val args: Array<out Any>?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NavigationData

        if (navigation != other.navigation) return false
        if (!args.contentEquals(other.args)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = navigation?.hashCode() ?: 0
        result = 31 * result + (args?.contentHashCode() ?: 0)
        return result
    }
}