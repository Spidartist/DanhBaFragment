package vn.edu.hust.activityexamples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ListFragment : Fragment() {

    val items = arrayListOf<Person>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val recyclerView = view.findViewById<ListView>(R.id.recycler_view)

        repeat(28) {
            val index = it + 1
            items.add(Person(
                index,
                "Hoang Danh Quan $index",
                resources.getIdentifier("ic_launcher_background", "drawable", activity?.packageName),
                "09888426$index"
            ))
        }

        val adapter = DanhBaItemAdapter(items)

        recyclerView.adapter = adapter
        recyclerView.setOnItemClickListener { _: AdapterView<*>, _: View, position : Int, _: Long ->
            val item = items[position]
            val bundle = Bundle()
            bundle.putParcelable("item", item)
            findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
        }

        registerForContextMenu(recyclerView)

        return view
    }
}