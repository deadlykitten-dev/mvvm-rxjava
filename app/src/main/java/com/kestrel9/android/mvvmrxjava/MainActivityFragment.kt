package com.kestrel9.android.mvvmrxjava

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.reactivex.disposables.Disposable

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {

    private val adapter = ListAdapter()
    private var subscribe: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val rvList = view.findViewById<RecyclerView>(R.id.rv_list)
        rvList.adapter = adapter
        setupItemClick()
        return view
    }

    private fun setupItemClick() {
        subscribe = adapter.clickEvent
                .subscribe {
                    Toast.makeText(context, "Clicked on $it", Toast.LENGTH_SHORT).show()
                }
    }

    override fun onDestroy() {
        super.onDestroy()
        subscribe?.dispose()
    }
}
