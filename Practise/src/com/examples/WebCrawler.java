package com.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WebCrawler {

	static Set<String> seenURL = ConcurrentHashMap.newKeySet();
	List<String> resultVisitedUrls = new ArrayList<>();
	ReadWriteLock lock_http_request = new ReentrantReadWriteLock();
	Lock readLock_http_request = lock_http_request.readLock();
	Lock writeLock_http_request = lock_http_request.writeLock();

	public boolean contains(String url) {
		readLock_http_request.lock();
		try {
			if (!seenURL.contains(url)) {
				return false;
			} else {
				return true;
			}
		} finally {
			readLock_http_request.unlock();
		}
	}

	public void addUrlToSeenURLSet(String url) {

		writeLock_http_request.lock();
		try {
			seenURL.add(url);

		} finally {
			writeLock_http_request.unlock();
		}
	}

	public List<String> getResultVisitedUrls() {
		return resultVisitedUrls;
	}

	public void crawl(String startUrl, HtmlParser htmlParser, WebCrawler crawler) throws Exception {

		if (!contains(startUrl)) {
			try {
				crawler.addUrlToSeenURLSet(startUrl);
				List<String> subUrls = htmlParser.getUrls(startUrl);

				resultVisitedUrls.add(startUrl + "  Done by thread - " + Thread.currentThread());

				for (String subUrl : subUrls) {
					crawl(subUrl, htmlParser, crawler);
				}
			} catch (Exception ex) {
				throw new Exception("Something went wrong. Method - crawl : " + ex.getMessage());
			}
		}

	}

	public static void main(String[] args) {

		class Crawl implements Callable<List<String>> {
			String startUrl;
			WebCrawler webCrawler;

			public Crawl(String startUrl, WebCrawler webCrawler) {
				this.startUrl = startUrl;
				this.webCrawler = webCrawler;
			}

			public List<String> call() {
				HtmlParser htmlParser = new RetrieveURLs();
				List<String> result = new ArrayList<>();
				try {
					webCrawler.crawl(startUrl, htmlParser, webCrawler);
					result = webCrawler.getResultVisitedUrls();
				} catch (Exception ex) {
					System.err.println("Some exception occurred in run() - " + ex.getMessage());
				}
				return result;
			}
		}

		ExecutorService service = Executors.newFixedThreadPool(4);
		try {
			WebCrawler webCrawler = new WebCrawler();
			WebCrawler webCrawler1 = new WebCrawler();

			Future<List<String>> future_1 = service
					.submit(new Crawl("http://localhost:3001/getUrls/google.com", webCrawler));
			Future<List<String>> future_2 = service
					.submit(new Crawl("http://localhost:3001/getUrls/google.com", webCrawler1));
			Future<List<String>> future_3 = service
					.submit(new Crawl("http://localhost:3001/getUrls/google.com", webCrawler1));

			List<String> result_1 = future_1.get();
			List<String> result_2 = future_2.get();
			List<String> result_3 = future_3.get();

			result_1.addAll(result_2);
			result_2.addAll(result_3);
			// Assert.assertEquals(6, result_1.size());
			System.out.println(result_1.size());
			for (String str : result_1) {
				System.out.println(str);
			}
			

		} catch (ExecutionException | InterruptedException ex) {

		} finally {
			service.shutdown();
		}

	}
}