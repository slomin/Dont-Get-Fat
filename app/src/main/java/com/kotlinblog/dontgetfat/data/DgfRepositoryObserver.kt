package com.kotlinblog.dontgetfat.data

/**
 * Classes implementing [DgfRepositoryObserver] must observe if Repo is doing DB operations
 */
interface DgfRepositoryObserver {
    fun observeRepo()
    fun stopObservingRepo()
}