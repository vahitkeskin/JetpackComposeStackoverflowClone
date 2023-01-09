package com.vahitkeskin.jetpackcomposestackoverflowclone.utils

import android.annotation.SuppressLint
import android.content.res.Resources
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import com.vahitkeskin.jetpackcomposestackoverflowclone.ui.theme.StackoverflowOrange
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.math.pow
import kotlin.random.Random

/**
 * @authot: Vahit Keskin
 * creared on 9.01.2023
 */

const val PARTICLE_LENGTH = 15
private val DENSITY = Resources.getSystem().displayMetrics.density
fun Int.dp2Px(): Float = (this * DENSITY)
private const val END_VALUE = 1.4f
private val X: Float = 5.dp2Px()
private val Y: Float = 20.dp2Px()
private val V: Float = 2.dp2Px()
private val W: Float = 1.dp2Px()

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.explodeOnClick(
    color: Color = StackoverflowOrange,
    durationMillis: Int = 1000,
    easing: Easing = LinearEasing,
    repeatable: Boolean = false,
    onClick: (() -> Unit)? = null
): Modifier = composed {
    val animationScope = rememberCoroutineScope()
    val factor = remember { Animatable(initialValue = 0f) }
    val alpha = remember { Animatable(initialValue = 0f) }
    var isComputed by remember { mutableStateOf(false) }
    var height = 0
    var width = 0
    val particles = remember { arrayOfNulls<Particle>(PARTICLE_LENGTH * PARTICLE_LENGTH) }

    this.then(
        onGloballyPositioned {
            if (!isComputed) {
                height = it.size.height
                width = it.size.width
            }
        }
            .drawWithContent {
                this.drawContent()
                for (particle in particles) {
                    particle?.let {
                        it.update(factor.value)
                        if (it.alpha > 0f) {
                            this.drawCircle(
                                color,
                                it.radius,
                                Offset(it.cx, it.cy)
                            )
                        }
                    }
                }
            }
            .alpha(1f - alpha.value)
            .clickable {
                animationScope.launch {
                    if (!isComputed) {
                        val random = Random(System.currentTimeMillis())
                        for (i in 0 until PARTICLE_LENGTH) {
                            for (j in 0 until PARTICLE_LENGTH) {
                                particles[(i * PARTICLE_LENGTH) + j] = generateParticle(
                                    random,
                                    height,
                                    width / 2,
                                    height / 2
                                )
                            }
                        }
                        isComputed = true
                    }
                    val result = async {
                        alpha.animateTo(
                            targetValue = 1f,
                            animationSpec = keyframes {
                                this.durationMillis = durationMillis
                                0.9f at durationMillis / 2
                            }
                        )
                    }

                    async {
                        factor.animateTo(
                            targetValue = 1.5f,
                            animationSpec = tween(durationMillis = durationMillis, easing = easing)
                        )
                    }

                    result.invokeOnCompletion {
                        if (repeatable) {
                            this.launch {
                                alpha.snapTo(0f)
                                factor.snapTo(0f)
                            }
                        }
                    }
                }
                onClick?.invoke()
            }
    )
}

/**
 * Particle data class
 */
internal data class Particle(
    var alpha: Float = 0f,
    var color: Color = Color.Black,
    var cx: Float = 0f,
    var cy: Float = 0f,
    var radius: Float = 0f,
    var baseCx: Float = 0f,
    var baseCy: Float = 0f,
    var baseRadius: Float = 0f,
    var top: Float = 0f,
    var bottom: Float = 0f,
    var mag: Float = 0f,
    var neg: Float = 0f,
    var life: Float = 0f,
    var overflow: Float = 0f
) {

    fun update(factor: Float) {
        var f = 0f
        var normalization: Float = factor / END_VALUE
        if (normalization < life || normalization > 1f - overflow) {
            alpha = 0f
            return
        }
        normalization = (normalization - life) / (1f - life - overflow)
        val f2: Float = normalization * END_VALUE
        if (normalization >= 0.7f) {
            f = (normalization - 0.7f) / 0.3f
        }
        alpha = 1f - f
        f = bottom * f2
        cx = baseCx + f
        cy = (baseCy - neg * f.toDouble().pow(2.0)).toFloat() - f * mag
        radius = V + (baseRadius - V) * f2
    }
}

internal fun generateParticle(
    random: Random,
    height: Int, centerX: Int, centerY: Int
): Particle {
    val particle = Particle()
    particle.radius = V
    if (random.nextFloat() < 0.2f) {
        particle.baseRadius = V + (X - V) * random.nextFloat()
    } else {
        particle.baseRadius = W + (V - W) * random.nextFloat()
    }
    val nextFloat = random.nextFloat()
    particle.top = height * (0.18f * random.nextFloat() + 0.2f)
    particle.top = if (nextFloat < 0.2f) particle.top else particle.top + particle.top * 0.2f * random.nextFloat()
    particle.bottom = height * (random.nextFloat() - 0.5f) * 1.8f
    var f = if (nextFloat < 0.2f) particle.bottom else if (nextFloat < 0.8f) particle.bottom * 0.6f else particle.bottom * 0.3f
    particle.bottom = f
    particle.mag = 4.0f * particle.top / particle.bottom
    particle.neg = -particle.mag / particle.bottom
    f = centerX + Y * (random.nextFloat() - 0.5f)
    particle.baseCx = f
    particle.cx = f
    f = centerY + Y * (random.nextFloat() - 0.5f)
    particle.baseCy = f
    particle.cy = f
    particle.life = END_VALUE / 10 * random.nextFloat()
    particle.overflow = 0.4f * random.nextFloat()
    particle.alpha = 1f
    return particle
}
