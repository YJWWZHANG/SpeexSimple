package com.zqb.speexlib;


public class Speex {

	/*
	 * quality
	 * 1 : 4kbps (very noticeable artifacts, usually intelligible)
	 * 2 : 6kbps (very noticeable artifacts, good intelligibility)
	 * 4 : 8kbps(noticeable artifacts sometimes)
	 * 6 : 11kpbs (artifacts usually onlynoticeable with headphones)
	 * 8 : 15kbps (artifacts not usually noticeable)
	 */

	//编码解码率
	private static final int QUALITY_1 = 1;
	private static final int QUALITY_2 = 2;
	private static final int QUALITY_3 = 3;
	private static final int QUALITY_4 = 4;
	private static final int QUALITY_6 = 6;
	private static final int QUALITY_8 = 8;
	//编码解码后得到包的大小
	private static final int BYTE_SIZE1 = 10;
	private static final int BYTE_SIZE2 = 15;
	private static final int BYTE_SIZE3 = 20;
	private static final int BYTE_SIZE4 = 20;
	private static final int BYTE_SIZE6 = 28;
	private static final int BYTE_SIZE8 = 38;

	private static final int DEFAULT_COMPRESSION = QUALITY_3;
	/*
	 * static { try { System.loadLibrary("speex"); } catch (Exception ule) {
	 * System.err.println("WARNING: Could not load library libspeex.so!"); } }
	 */

	/*
	 * public void setQuality(int quality) { load(); open(quality); }
	 */

	private static Speex mSpeex;
	static {
		load();
	}
	public static Speex newInstance() {
		if (mSpeex == null) {
			mSpeex = new Speex();
		}
		return mSpeex;
	}

	public Speex() {
//		load();
		open(DEFAULT_COMPRESSION);
	}

	public  static void load() {
		try {
			System.loadLibrary("speex");
		} catch (Exception ule) {
			System.err.println("WARNING: Could not load library libspeex.so!");
		}
	}

	/**
	 * @Description 获取编码解码包大小
	 * @return
	 * @date 2016年4月11日 下午2:00:50
	 */
	public static int getSize() {
		int size = 0;
		switch (DEFAULT_COMPRESSION) {
			case QUALITY_1:
				size = BYTE_SIZE1;
				break;
			case QUALITY_2:
				size = BYTE_SIZE2;
				break;
			case QUALITY_3:
				size = BYTE_SIZE3;
				break;
			case QUALITY_4:
				size = BYTE_SIZE4;
				break;
			case QUALITY_6:
				size = BYTE_SIZE6;
				break;
			case QUALITY_8:
				size = BYTE_SIZE8;
				break;

			default:
				size = BYTE_SIZE1;
				break;
		}
		return size;
	}

	/**
	 * @Description 获取组包大小
	 * @return
	 * @date 2016年4月11日 下午2:00:50
	 */
	public static int getGroupSize() {
		int gSize = 0;
		switch (DEFAULT_COMPRESSION) {
			case QUALITY_1:
				gSize = 40;
				break;
			case QUALITY_2:
				gSize = 30;
				break;
			case QUALITY_4:
				gSize = 25;
				break;
			case QUALITY_6:
				gSize = 20;
				break;
			case QUALITY_8:
				gSize = 15;
				break;

			default:
				gSize = 10;
				break;
		}
		return gSize;
	}

	public native int open(int compression);

	public native int getFrameSize();

	public native int decode(byte encoded[], short lin[], int size);

	public native int encode(short lin[], int offset, byte encoded[], int size);

	public native void close();

	/* echo cancellation */
	// public static native int echoinit(int framesize,int filterlength);
	// public static native int echoplayback(short[] play);
	// public static native int echocapture(short[] rec ,short[] out);
	// public static native void echoclose();

}