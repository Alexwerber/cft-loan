package com.example.cft_loan.ui.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.cft_loan.R
import com.example.cft_loan.data.entities.LoanCondition
import com.example.cft_loan.di.constants.BundleKeys.MAX_AMOUNT
import com.example.cft_loan.di.constants.BundleKeys.PERCENT
import com.example.cft_loan.di.constants.BundleKeys.PERIOD
import com.example.cft_loan.ui.fragments.CreateLoanFragment
import kotlinx.android.synthetic.main.item_loan_condition.view.*

class LoansConditionsAdapter: RecyclerView.Adapter<LoansConditionsAdapter.ViewHolder>() {
    private lateinit var context: FragmentActivity
    private var loansConditions: List<LoanCondition> = emptyList()

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

        holder.conditionPeriod.text = loanCondition.period.toString()
        holder.conditionPercent.text = loanCondition.percent.toString()
        holder.conditionAmount.text = loanCondition.maxAmount.toString()

        holder.root.setOnClickListener() {
            val createLoanFragment = CreateLoanFragment()
            val bundle: Bundle = fillValuesForCreateLoan(
                                            loanCondition.percent,
                                            loanCondition.period,
                                            loanCondition.maxAmount)
            createLoanFragment.arguments = bundle

            context.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, createLoanFragment)
                            .addToBackStack(null)
                            .commit()
        }
    }

    private fun fillValuesForCreateLoan(percent: Double, period: Int, maxAmount: Long): Bundle = Bundle().apply {
        putDouble(PERCENT, percent)
        putInt(PERIOD, period)
        putLong(MAX_AMOUNT, maxAmount)
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