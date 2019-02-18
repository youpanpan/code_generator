package com.chengxuunion.util.id;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;



/**
 * SnowFlake算法 64位Long类型生成唯一ID 第一位0，表明正数 2-42，41位，表示毫秒时间戳差值，起始值自定义
 * 43-52，10位，机器编号，5位数据中心编号，5位进程编号 53-64，12位，毫秒内计数器 本机内存生成，性能高
 * 
 * 主要就是三部分： 时间戳，进程id，序列号 时间戳41，id10位，序列号12位
 *
 * @author kutome
 * @date 2018年8月14日
 * @version V1.0
 */
public class IdGenerator {
	
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	private final static long beginTs = 1483200000000L;
 
	private long lastTs = 0L;
 
	private long processId;
	private int processIdBits = 10;
 
	private long sequence = 0L;
	private int sequenceBits = 12;

	private IdGenerator() {
		
		this(new Random().nextInt(300));
	}
 
	// 10位进程ID标识
	private IdGenerator(long processId) {
		if (processId > ((1 << processIdBits) - 1)) {
			throw new RuntimeException("进程ID超出范围，设置位数" + processIdBits + "，最大"
					+ ((1 << processIdBits) - 1));
		}
		this.processId = processId;
	}

	static class Singleton {
		private static IdGenerator instance = new IdGenerator();
	}

	public static IdGenerator getInstance() {
		return Singleton.instance;
	}
 
	protected long timeGen() {
		return System.currentTimeMillis();
	}
 
	public synchronized long nextId() {
		long ts = timeGen();
		if (ts < lastTs) {// 刚刚生成的时间戳比上次的时间戳还小，出错
			throw new RuntimeException("时间戳顺序错误");
		}
		if (ts == lastTs) {// 刚刚生成的时间戳跟上次的时间戳一样，则需要生成一个sequence序列号
			// sequence循环自增
			sequence = (sequence + 1) & ((1 << sequenceBits) - 1);
			// 如果sequence=0则需要重新生成时间戳
			if (sequence == 0) {
				// 且必须保证时间戳序列往后
				ts = nextTs(lastTs);
			}
		} else {// 如果ts>lastTs，时间戳序列已经不同了，此时可以不必生成sequence了，直接取0
			sequence = 0L;
		}
		lastTs = ts;// 更新lastTs时间戳
		return ((ts - beginTs) << (processIdBits + sequenceBits)) | (processId << sequenceBits)
				| sequence;
	}
	
	public synchronized String nextIdString() {
		return String.valueOf(nextId());
	}
 
	protected long nextTs(long lastTs) {
		long ts = timeGen();
		while (ts <= lastTs) {
			ts = timeGen();
		}
		return ts;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IdGenerator ig = new IdGenerator(1);
		
		Set<Long> set = new HashSet<Long>();
		long begin = System.nanoTime();
		for (int i = 0; i < 100; i++) {
			set.add(ig.nextId());
		}
		System.out.println("time=" + (System.nanoTime() - begin)/1000.0 + " us");
		System.out.println(set.size());
		System.out.println(set);
	}

}
