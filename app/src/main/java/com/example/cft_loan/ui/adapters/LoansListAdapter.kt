package com.example.cft_loan.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.cft_loan.R
import com.example.cft_loan.data.entities.Loan
import com.example.cft_loan.ui.fragments.loan.LoanInfoFragment
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

        holder.anount.text = compareString(postedLoan.amount.toString(), " ₽")
        holder.percent.text = compareString(postedLoan.percent.toString(), " %")
        holder.status.text = postedLoan.state.toString()

        when (postedLoan.state) {
            "APPROVED" -> holder.status.setTextColor(Color.GREEN)
            "REJECTED" -> holder.status.setTextColor(Color.RED)
        }

        holder.root.setOnClickListener() {
            context.supportFragmentManager
                .beginTransaction()
                        .add(R.id.fragment_container, LoanInfoFragment())
                        .addToBackStack(null)
                        .commit()
        }
    }

    override fun getItemCount(): Int = loansList.size

    class LoanViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val anount = itemView.post_loan_amount
        val percent = itemView.post_loan_percent
        val status = itemView.post_loan_status
        val root = itemView.rootView
    }

    private fun compareString(string: String, secondString: String): String =
        "$string $secondString"

}