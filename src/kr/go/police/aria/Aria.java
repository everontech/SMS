package kr.go.police.aria;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;

import kr.go.police.SMSUtil;

import org.apache.http.util.ByteArrayBuffer;

public class Aria {
	/* aria */
    public static final String MASTER_KEY = "everontech";	// 마스터 키	
    public static final short MASTER_KEY_LENGTH = 32;		// 마스터 키 바이트수    
    public static final short MASTER_KEY_BIT_LENGTH = 192;
    public static final int ENCRPTY_LENGTH = 128;
    private static Aria instance;
	private byte[] masterkey;	// DEBUG용
	ARIAEngine ariaEngine;
	
	private Aria(){
		// TODO Auto-generated constructor stub
		this.masterkey = setMasterKey(MASTER_KEY);
		//Log.i(BaseActivity.DEBUG_TAG, "master key1 : " + masterkey.toString() );
	    try {
			this.ariaEngine = new ARIAEngine(MASTER_KEY_BIT_LENGTH);
		    this.ariaEngine.setKey(this.masterkey);
		    this.ariaEngine.setupRoundKeys();			
		} catch (InvalidKeyException e) {}			
	}
	
	/**
	 * 싱글턴객체
	 * @return
	 */
	public static Aria getInstance(){
		if(instance == null)
			instance = new Aria();
		return instance;
	}

	private byte[] setMasterKey(String key){
		ByteBuffer bytebuffer = ByteBuffer.allocate(MASTER_KEY_LENGTH);	
		bytebuffer.put(key.getBytes());		
		return bytebuffer.array();	  
	}
	
	/**
	 * 암호화된 바이트를 헥사 스트링으로 변환
	 * @return
	 */
	public String encryptByte2HexStr(String str){
		if(str == null || str.length() <= 0){
			return null;
		}
		ByteBuffer bytebuffer = ByteBuffer.allocate(ENCRPTY_LENGTH);	
		bytebuffer.put(str.getBytes());			
		byte[] enBtes = this.encryptPacket(bytebuffer.array(), ENCRPTY_LENGTH);
		return SMSUtil.byteArrayToHex(enBtes);
	}
	
	/**
	 * 암호화된 헥사 스트링을 복호화
	 * @return
	 */
	public String encryptHexStr2DecryptStr(String str){
		if(str == null || str.length() <= 0){
			return null;
		}
		
		ByteBuffer bytebuffer = ByteBuffer.allocate(ENCRPTY_LENGTH);	
		bytebuffer.put(SMSUtil.hexToByteArray(str));						
		byte[] bytes = this.decryptPacket(bytebuffer.array(), ENCRPTY_LENGTH);
		return new String(bytes);		
	}	
	
	
	public byte[] encryptPacket(byte[] plain, int encryptSize ){
		byte[] encrypted = new byte[16];	// 암호를 넣을  byte
        byte[] encrypt = new byte[16];		
		ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(encryptSize); //암호화될 사이즈만큼 초기화 
		//byteArrayBuffer.clear();	// 바이트배열을 비워줌
        int nPlainBytes = plain.length;	// plain 문자열 길이값
        //  16개 단위로 잘라 블럭 생성
        //  16으로 나눈 나머지가 있으면 블럭 크키 +1 추가 
        int nBlockNum = (nPlainBytes % 16 == 0) ? (nPlainBytes / 16) : (nPlainBytes / 16 + 1);
        int cnt = 0;
		try {
			// 블럭 크기만큼 16바이트씩 잘라서 복호화 시킴			
	        for(int i = 0; i < nBlockNum ; i++) {
	            for(int j =i*16; j < ((i+1)*16); j++)
	            	encrypt[cnt++] = plain[j];	
	            cnt = 0;
	            ariaEngine.encrypt(encrypt, 0, encrypted, 0); // 암호생성
	            //Log.i(BaseActivity.DEBUG_TAG, "encrypting-" + i );
	        	byteArrayBuffer.append( encrypted, 0, encrypted.length );	//	바이트 배열에 추가 
	        }				
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			//Log.i(BaseActivity.DEBUG_TAG, "error_encryptPacket : " + ARIAEngine.bytesToHex(byteArrayBuffer.toByteArray()) );
			return null;
		}
		//Log.i(BaseActivity.DEBUG_TAG, "encrypted" );
		//Log.i(BaseActivity.DEBUG_TAG, "encrypt-byteToHex : " + ARIAEngine.bytesToHex(byteArrayBuffer.toByteArray()) );
		return byteArrayBuffer.toByteArray();
		
	}
	
	public byte[] decryptPacket(byte[] encrypted, int decryptSize ){
		byte[] plain = new byte[16];	// 복호를 넣을 byte
		byte[] temp = new byte[16];	
		ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(decryptSize); //복호화될 사이즈만큼 초기화 
        int nEncryptBytes = encrypted.length;	// plain 문자열 길이값
        //  16개 단위로 잘라 블럭 생성
        //  16으로 나눈 나머지가 있으면 블럭 크키 +1 추가 
        int nBlockNum = (nEncryptBytes % 16 == 0) ? (nEncryptBytes / 16) : (nEncryptBytes / 16 + 1);
        int cnt = 0;
		try {
			// 블럭 크기만큼 16바이트씩 잘라서 복호화 시킴
	        for (int i = 0; i < nBlockNum ; i++) {	
	            for(int j =i*16; j < ((i+1)*16); j++)
	            	plain[cnt++] = encrypted[j];
	            cnt = 0;
	        	ariaEngine.decrypt(plain, 0, temp, 0);
	        //	Log.i(BaseActivity.DEBUG_TAG, "decrypting-" + i );
	        	byteArrayBuffer.append( temp, 0, temp.length );
	        }			
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
		//	Log.i(BaseActivity.DEBUG_TAG, "decryptPacket-error : " + e.getMessage() );
			return null;
		}		
		//Log.i(BaseActivity.DEBUG_TAG, "decrypted" );
		//Log.i(BaseActivity.DEBUG_TAG, "decrypt : " + (new String(byteArrayBuffer.toByteArray())).trim() );
		return byteArrayBuffer.toByteArray();	
		
	}
	
}
