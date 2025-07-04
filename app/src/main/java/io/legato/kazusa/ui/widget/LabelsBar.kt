package io.legato.kazusa.ui.widget

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import io.legato.kazusa.utils.themeColor

@Suppress("unused", "MemberVisibilityCanBePrivate")
class LabelsBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ChipGroup(context, attrs) {

    private val unUsedChips = arrayListOf<Chip>()
    private val usedChips = arrayListOf<Chip>()

    fun setLabels(labels: List<String>) {
        clear()
        labels.forEach {
            addLabel(it)
        }
    }

    fun setLabels(labels: Array<String>) {
        setLabels(labels.toList())
    }

    fun clear() {
        unUsedChips.addAll(usedChips)
        usedChips.clear()
        removeAllViews()
    }

    fun addLabel(label: String) {
        val chip = if (unUsedChips.isEmpty()) {
            Chip(context).apply {
                isClickable = false
                isCheckable = false
                chipStartPadding = 8f
                chipEndPadding = 8f
                chipStrokeWidth = 0f

                chipBackgroundColor = ColorStateList.valueOf(
                    context.themeColor(com.google.android.material.R.attr.colorPrimaryContainer)
                )

                usedChips.add(this)
            }
        } else {
            unUsedChips.removeAt(unUsedChips.lastIndex).also {
                usedChips.add(it)
            }
        }
        chip.text = label
        addView(chip)
    }
}
