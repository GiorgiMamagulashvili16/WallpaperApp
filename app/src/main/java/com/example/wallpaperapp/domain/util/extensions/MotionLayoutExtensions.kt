package com.example.wallpaperapp.domain.util.extensions

import androidx.constraintlayout.motion.widget.MotionLayout

fun MotionLayout.setActionOnSpecifiedProgress(
    transitionProgress: Float, actionBeforeProgress: () -> Unit, actionAfterProgress: () -> Unit
) {
    addTransitionListener(object : MotionLayout.TransitionListener {
        override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {

        }
        override fun onTransitionChange(
            motionLayout: MotionLayout?,
            startId: Int,
            endId: Int,
            progress: Float
        ) {
            if (progress > transitionProgress)
                actionAfterProgress.invoke()
            else
                actionBeforeProgress.invoke()
        }

        override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {}

        override fun onTransitionTrigger(
            motionLayout: MotionLayout?,
            triggerId: Int,
            positive: Boolean,
            progress: Float
        ) {
        }
    })
}