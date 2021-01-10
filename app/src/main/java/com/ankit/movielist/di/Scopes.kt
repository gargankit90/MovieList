package com.ankit.movielist.di

import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.RUNTIME

@Scope
@Retention(RUNTIME)
annotation class ApplicationScope

@Scope
@Retention(RUNTIME)
annotation class FeatureScope
