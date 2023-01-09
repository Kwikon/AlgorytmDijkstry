package com.example.algorytmdijkstry

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.algorytmdijksty.Dijkstra
import com.example.algorytmdijksty.Graph
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var startPoint: NumberPicker
    private lateinit var endPoint: NumberPicker
    private lateinit var nodeCost: NumberPicker
    private lateinit var isConnected: NumberPicker
    private lateinit var connectButton: Button
    private lateinit var applyButton: Button
    private lateinit var graphButton: Button
    private lateinit var outcomeTextView: TextView
    private lateinit var graphTextView: TextView
    private lateinit var g: Graph

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startPoint = findViewById(R.id.startPoint)
        endPoint = findViewById(R.id.endPoint)
        nodeCost = findViewById(R.id.nodeCost)
        isConnected = findViewById(R.id.isConnected)
        applyButton = findViewById(R.id.applyButton)
        graphButton = findViewById(R.id.graphButton)
        connectButton = findViewById(R.id.connectButton)
        outcomeTextView = findViewById(R.id.outcomeTextView)
        graphTextView = findViewById(R.id.graphView)

        g = Graph(10, 5, 10)

        graphButton.setOnClickListener {
            graphTextView.text = g.adjacencyList.toString() + '\n' + '\n' + g.edgesList.toString()
        }

        applyButton.setOnClickListener {
            val dijkstra: Dijkstra = Dijkstra(g)
            val path = dijkstra.pathfind(startPoint.value, endPoint.value)

            outcomeTextView.text = path.toString() ?: "No path available"
        }

        connectButton.setOnClickListener {
            if (isConnected.value == 1) {
                g.addEdge(startPoint.value, endPoint.value, nodeCost.value)
            }
            else {
                g.removeEdge(startPoint.value, endPoint.value, nodeCost.value)
            }
        }

        startPoint.minValue = 0
        startPoint.maxValue = 9
        endPoint.minValue = 0
        endPoint.maxValue = 9
        nodeCost.minValue = 0
        nodeCost.maxValue = 9
        isConnected.minValue = 0
        isConnected.maxValue = 1
    }

}
