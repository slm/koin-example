package com.slmyldz.project1.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.slmyldz.project1.R
import com.slmyldz.project1.network.User
import com.slmyldz.project1.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class MainFragment : Fragment(), OnItemClick {

    companion object {
        fun newInstance() = MainFragment()
    }

    val viewModel: MainViewModel by sharedViewModel()

    val adapter = UserAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapter
    }

    override fun onOnItemClick(user: User) {
        context?.startActivity(DetailActivity.getIntent(requireContext(),user))
    }

    private val listObserver = Observer<MutableList<User>> { value ->
        value?.let {
            adapter.list = it
            adapter.notifyDataSetChanged()
            pb.visibility = View.GONE
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.currentList.observe(viewLifecycleOwner, listObserver)
        pb.visibility = View.VISIBLE
        viewModel.getUsers()
        viewModel.getUsers()
    }

}
