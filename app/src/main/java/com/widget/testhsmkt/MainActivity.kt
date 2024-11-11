package com.widget.testhsmkt

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.widget.testhsmkt.implementation.Samek_9BContextObject
import com.widget.testhsmkt.implementation.Samek_9BMediator
import com.widget.testhsmkt.implementation.Samek_9BQHsmScheme
import com.widget.testhsmkt.implementation.Samek_9BWrapper
import com.widget.testhsmkt.support.GuiLogger
import com.widget.testhsmkt.support.Interceptor
import com.widget.testhsmkt.support.Logger

class MainActivity : AppCompatActivity() {

    val TAG: String = "hsm"

    private var contextObject: Samek_9BContextObject? = null
    private val logger: Logger = Logger()
    private val contextLogger: GuiLogger = GuiLogger(this)
    private val interceptor: Interceptor = Interceptor()

    private lateinit var stringAdapter: StringAdapter
    private lateinit var buttonAdapter: ButtonAdapter
    private lateinit var verticalRecyclerView: RecyclerView
    private lateinit var horizontalRecyclerView: RecyclerView

    private val stringList : MutableList<String> = mutableListOf();
    private val buttonTexts = listOf("A", "B", "C", "D", "E", "F", "G", "H")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        createStateMachine()
        setupLayout()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initStateMachine();

    }

    private fun createStateMachine() {
        Log.d(TAG, "******* initStateMachine *******")
        contextObject = Samek_9BContextObject(contextLogger)
        val mediator: Samek_9BMediator = Samek_9BMediator(contextObject!!, interceptor, contextLogger)
        val hsmStateMachine: Samek_9BQHsmScheme = Samek_9BQHsmScheme(mediator, logger)
        Samek_9BWrapper(hsmStateMachine, mediator)

    }

    private fun initStateMachine() {
        contextObject!!.init()
    }

    private fun setupLayout() {
        verticalRecyclerView = findViewById(R.id.verticalRecyclerView)
        verticalRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // Add dividers to the verticalRecyclerView
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        verticalRecyclerView.addItemDecoration(dividerItemDecoration)

        stringAdapter = StringAdapter(stringList)
        verticalRecyclerView.adapter = stringAdapter

        horizontalRecyclerView = findViewById(R.id.horizontalRecyclerView)
        horizontalRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        buttonAdapter = ButtonAdapter(buttonTexts, contextObject!!)
        horizontalRecyclerView.adapter = buttonAdapter

        // Enable swipe-to-dismiss
        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                stringAdapter.removeString(position)
            }
        })
        itemTouchHelper.attachToRecyclerView(verticalRecyclerView)

    }

    fun addStringToRecyclerView(newString: String?) {
        stringAdapter.addString(newString!!)
        verticalRecyclerView.scrollToPosition(stringAdapter.itemCount - 1)
    }
}