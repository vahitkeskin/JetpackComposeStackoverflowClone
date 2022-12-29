package com.vahitkeskin.jetpackcomposestackoverflowclone.model.homemodel

import com.vahitkeskin.jetpackcomposestackoverflowclone.screens.IViewState

/**
 * @authot: Vahit Keskin
 * creared on 28.12.2022
 */
data class HomeDetailViewState(
    val isLoading: Boolean = false,
    val data: Item? = null
) : IViewState