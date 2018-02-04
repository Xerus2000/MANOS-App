package xerus.tests

import android.app.ListActivity
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.ViewGroup
import android.widget.*
import com.woxthebox.draglistview.DragItemAdapter
import com.woxthebox.draglistview.DragListView
import kotlinx.android.synthetic.main.activity_main.*
import xerus.ktutil.pair

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        /*val list = arrayListOf("hi", "greetings", "bye")
        val lv = ListView(this)
        lv.adapter = ArrayAdapter(this, R.layout.list_item, list)
    
        // listening to single list item on click
        lv.setOnItemClickListener { parent, view, position, id ->
            // selected item
            val product = (view as TextView).text.toString()
    
            // Launching new Activity on selecting single List Item
            val i = Intent(applicationContext, SingleListItem::class.java)
            // sending data to new activity
            i.putExtra("product", product)
            startActivity(i)
        }
        
        setContentView(lv)*/
    
        val mDragListView = drag_list_view
        mDragListView.setDragListListener(object : DragListView.DragListListenerAdapter() {
            override fun onItemDragStarted(position: Int) {
                Toast.makeText(this@MainActivity, "Start - position: " + position, Toast.LENGTH_SHORT).show()
            }
        
            override fun onItemDragEnded(fromPosition: Int, toPosition: Int) {
                if (fromPosition != toPosition) {
                    Toast.makeText(this@MainActivity, "End - position: " + toPosition, Toast.LENGTH_SHORT).show()
                }
            }
        })
        
        val list = ArrayList(listOf("hi", "greetings", "bye").mapIndexed { index: Int, s: String -> index.toLong().pair { s } })
        mDragListView.setAdapter(ItemAdapter(list, R.layout.list_item, R.id.list_item, true), true)
    
        mDragListView.setLayoutManager(LinearLayoutManager(this))
        mDragListView.setCanDragHorizontally(false)
    }
    
    
}
