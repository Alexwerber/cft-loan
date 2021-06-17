package com.example.cft_loan.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.cft_loan.R
import com.example.cft_loan.data.entities.LoanCondition
import kotlinx.android.synthetic.main.item_loan_condition.view.*

class LoansConditionsAdapter: RecyclerView.Adapter<LoansConditionsAdapter.ViewHolder>() {
    private lateinit var context: FragmentActivity
    private var loansConditions: List<LoanCondition?> = emptyList()

    fun setData(list: List<LoanCondition>) {
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
        val loanCondition = loansConditions[position]

        holder.conditionPeriod.text = loanCondition?.period.toString()
        holder.conditionPercent.text = loanCondition?.percent.toString()
        holder.conditionAmount.text = loanCondition?.maxAmount.toString()
    }

    override fun getItemCount(): Int {
        return loansConditions.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val conditionPeriod: TextView = itemView.condition_period
        val conditionPercent = itemView.condition_percent
        val conditionAmount = itemView.condition_max_amount
        val root = itemView.rootView
    }
}