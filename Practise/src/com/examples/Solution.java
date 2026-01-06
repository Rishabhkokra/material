package com.examples;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.time.*;

interface Worker {

	boolean performTask();

}

public class Solution {

	private static int allowedMaxJob = 10;
	private static int jobInProgress = 0;

	public static void main(String[] args) throws InterruptedException {

		List<Job> jobList = new ArrayList<>();
		List<Thread> jobInProgressList = new CopyOnWriteArrayList<>();

		Function<JobData, Integer> function1 = (op) -> op.getX() * op.getY();
		JobData JobData = new JobData(10, 20);

		Worker Task = new Task(function1, JobData);
		Worker task2 = new Task((op) -> op.getX() + op.getY(), JobData);
		Worker task3 = new Task((op) -> op.getX() % op.getY(), JobData);
		Job job1 = new Job("job1", 2, 0, Task, LocalDateTime.now());
		Job job2 = new Job("job2", 2, 0, Task, LocalDateTime.now());
		Job job3 = new Job("job3", 2, 0, task2, LocalDateTime.now());
		Job job4 = new Job("job4", 2, 0, task2, LocalDateTime.now());
		Job job5 = new Job("job5", 2, 0, task3, LocalDateTime.now());
		Job job6 = new Job("job6", 2, 0, task3, LocalDateTime.now());
		Job job7 = new Job("job7", 2, 0, task3, LocalDateTime.now());
		

		jobList.add(job1);
		jobList.add(job2);
		jobList.add(job3);
		jobList.add(job4);
		jobList.add(job5);
		jobList.add(job6);
		jobList.add(job7);

		while (true) {

			for (Job job : jobList) {
				if ((job.getStartTime().equals(LocalDateTime.now()) || job.getStartTime().isBefore(LocalDateTime.now()))
						&& (jobInProgress <= allowedMaxJob)) {
					Thread thread = new JobExecuter(job);
					thread.setName(job.getName());
					Thread.sleep(500);
					thread.start();
					jobInProgress++;
					jobInProgressList.add(thread);

				}
			}

			for (Thread jobInprogresss : jobInProgressList) {
				jobInprogresss.join();
			}

			for (Thread jobInprogresss : jobInProgressList) {
				if (!jobInprogresss.isAlive()) {
					jobInProgress--;
					jobList.removeIf(i -> i.getName().equalsIgnoreCase(jobInprogresss.getName()));
					jobInProgressList.remove(jobInprogresss);
					System.out.println("deleted thread: " + jobInprogresss.getName());

				}
			}

		}

	}
}

class Job {

	private String name;
	private int retry;
	private int timout;
	private Worker task;
	private LocalDateTime startTime;

	public LocalDateTime getStartTime() {
		return this.startTime;
	}

	public Job(String name, int retry, int timeout, Worker task, LocalDateTime startTime) {
		this.name = name;
		this.retry = retry;
		this.timout = timeout;
		this.task = task;
		this.startTime = startTime;
	}

	public Worker getTask() {
		return this.task;
	}

	public int getRetry() {
		return this.retry;
	}

	public int getTimeOut() {
		return this.timout;
	}

	public String getName() {
		return this.name;
	}

}

class Task implements Worker {

	private Function<JobData, Integer> function;
	private JobData JobData;

	public Task(Function<JobData, Integer> function, JobData JobData) {
		this.function = function;
		this.JobData = JobData;
	}

	public boolean performTask() {
		System.out.println("output: " + function.apply(JobData));
		return true;
	}

}

class JobExecuter extends Thread {

	private Job job;

	public JobExecuter(Job job) {
		this.job = job;
	}

	@Override
	public void run() {
		System.out.println("starting job:: " + job.getName());
		job.getTask().performTask();
		System.out.println("completed job:: " + job.getName());
	}
}

class JobData {

	private int x;
	private int y;

	public JobData(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
}
