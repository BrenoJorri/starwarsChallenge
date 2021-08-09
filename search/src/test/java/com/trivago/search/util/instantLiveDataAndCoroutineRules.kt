package com.trivago.search.util

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.trivago.test.InstantCoroutineDispatcherRule
import org.junit.rules.RuleChain

val instantLiveDataAndCoroutineRules: RuleChain
    get() = RuleChain
        .outerRule(InstantCoroutineDispatcherRule())
        .around(InstantTaskExecutorRule())