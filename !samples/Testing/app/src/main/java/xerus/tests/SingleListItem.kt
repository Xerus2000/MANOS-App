package xerus.tests

/**
 * Created by Janek on 06.01.2018.
 */
import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class SingleListItem : Activity() {
	public override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		this.setContentView(R.layout.single_list_item_view)
		
		val txtProduct = findViewById<TextView>(R.id.product_label)
		
		// getting attached intent data
		val product = intent.getStringExtra("product")
		// displaying selected product name
		txtProduct.text = product
	}
}