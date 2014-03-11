package com.mywiki.util;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
/**
 * PriorityQueue优先级队列
 * @author mywiki95@gmail.com
 *
 */
public class PriorityQueueExample {
	private String name;
	private int population;

	public PriorityQueueExample(String name, int population) {
		this.name = name;
		this.population = population;
	}

	public String getName() {
		return this.name;
	}

	public int getPopulation() {
		return this.population;
	}

	public String toString() {
		return getName() + " - " + getPopulation();
	}

	public static void main(String args[]) {
		/**
		 * 想实现按照自己的意愿进行优先级排列的队列的话，需要实现Comparator接口
		 */
		Comparator<PriorityQueueExample> OrderIsdn = new Comparator<PriorityQueueExample>() {
			public int compare(PriorityQueueExample o1, PriorityQueueExample o2) {
				int numbera = o1.getPopulation();
				int numberb = o2.getPopulation();
				if (numberb > numbera) {
					return 1;
				} else if (numberb < numbera) {
					return -1;
				} else {
					return 0;
				}
			}
		};

		Queue<PriorityQueueExample> priorityQueue = new PriorityQueue<PriorityQueueExample>(
				11, OrderIsdn);

		PriorityQueueExample t1 = new PriorityQueueExample("t1", 1);
		PriorityQueueExample t3 = new PriorityQueueExample("t3", 3);
		PriorityQueueExample t2 = new PriorityQueueExample("t2", 2);
		PriorityQueueExample t4 = new PriorityQueueExample("t4", 0);
		priorityQueue.add(t1);
		priorityQueue.add(t3);
		priorityQueue.add(t2);
		priorityQueue.add(t4);
		while(!priorityQueue.isEmpty())
			System.out.println(priorityQueue.poll().toString());
	}
}
