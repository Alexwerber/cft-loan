package com.example.cft_loan.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.cft_loan.R
import com.example.cft_loan.data.entities.LoanCondition

class LoansConditionsAdapter: RecyclerView.Adapter<LoansConditionsAdapter.ViewHolder>() {
    private lateinit var context: FragmentActivity
    private var loansConditions: MutableList<LoanCondition?> = mutableListOf()

    fun setData(list: MutableList<LoanCondition?>) {
        loansConditions = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LoansConditionsAdapter.ViewHolder {
        context = parent.context as FragmentActivity

        val view = LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_loan_condition, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: LoansConditionsAdapter.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return loansConditions.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
}