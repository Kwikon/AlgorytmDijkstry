package com.example.algorytmdijksty

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

data class Cost(val node: Int, val cost: Int)

class Dijkstra(graph: Graph) : Algorithm(graph) {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun pathfind(s: Int, e: Int):Path? {
        val previous = MutableList(graph.nCount) { -1 }
        val costs = MutableList(graph.nCount) { -1 }
        costs[s] = 0;

        val queue = PriorityQueue<Cost>(compareBy{ it.cost })
        queue.add(Cost(s,0))

        while (!queue.isEmpty()) {
            val current = queue.remove()

            for (vertex in graph.adjacencyList[current.node] ){
                if (current.cost + vertex.cost > costs[vertex.node]
                    && costs[vertex.node] != -1) continue

                costs[vertex.node] = current.cost + vertex.cost
                previous[vertex.node] = current.node
                queue.add(Cost(vertex.node, costs[vertex.node]))
            }
        }

        if(previous[e] == -1)
            return null

        var path: MutableList<Int> = mutableListOf()
        var i = e
        do {
            path.add(i)
            i = previous[i]
        } while(i != s)

        return Path(path.reversed(), costs[e])
    }
}