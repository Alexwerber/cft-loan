package com.example.cft_loan.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.cft_loan.R
import com.example.cft_loan.data.entities.Loan
import kotlinx.android.synthetic.main.item_loans_list.view.*

class LoansListAdapter: RecyclerView.Adapter<LoansListAdapter.LoanViewHolder>() {
    private lateinit var context: FragmentActivity
    private var loansList: List<Loan> = emptyList()

    fun setData(list: List<Loan>) {
        loansList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoansListAdapter.LoanViewHolder {
        context = parent.context as FragmentActivity

        val view = LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_loans_list, parent, false)

        return LoanViewHolder(view)
    }

    override fun onBindViewHolder(holder: LoansListAdapter.LoanViewHolder, position: Int) {
        val postedLoan = loansList[position]

        holder.anount.text = postedLoan.amount.toString()
        holder.percent.text = postedLoan.percent.toString()
        holder.period.text = postedLoan.period.toString()
        holder.status.text = postedLoan.state

        holder.root.setOnClickListener() {
            //go to fragment with info about loan
        }
    }

    override fun getItemCount(): Int = loansList.size

    class LoanViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val anount = itemView.post_loan_amount
        val period = itemView.post_loan_period
        val percent = itemView.post_loan_percent
        val status = itemView.post_loan_status
        val root = itemView.rootView
    }

}