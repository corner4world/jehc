package jehc.xtmodules.xtcore.allutils.file.ftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
 * FTP工具类
 * 
 * @author dengcj
 *
 */
public class FtpUtil extends FtpConfig{
	/**
	 * @param path FTP服务器保存目录
	 * @param filename上传到FTP服务器上的文件名
	 * @param input 输入流
	 * @return 成功返回true，否则返回false
	 */
	public static boolean uploadFile(String path, String fname,InputStream input) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(getUrl(), getPort());
			ftp.login(getUsername(), getPassword());
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.changeWorkingDirectory(path);
			ftp.storeFile(fname, input);
			input.close();
			ftp.logout();
			success = true;
		} catch (IOException e) {
			throw new ExceptionUtil("上传出现异常"+e.getMessage());
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					throw new ExceptionUtil("上传出现异常"+ioe.getMessage());
				}
			}
		}
		return success;
	}

	/**
	 * @param remotePath FTP服务器上的相对路径
	 * @param 下载文件名
	 * @param localPath 下载后保存到本地的路径
	 * @return
	 */
	public static boolean downFile(String remotePath,String fname, String localPath) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(getUrl(), getPort());
			ftp.login(getUsername(), getPassword());
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.changeWorkingDirectory(remotePath);
			FTPFile[] fs = ftp.listFiles();
			for (FTPFile ff : fs) {
				if (ff.getName().equals(fname)) {
					File localFile = new File(localPath + "/" + ff.getName());
					OutputStream is = new FileOutputStream(localFile);
					ftp.retrieveFile(ff.getName(), is);
					is.close();
				}
			}
			ftp.logout();
			success = true;
		} catch (IOException e) {
			throw new ExceptionUtil("下载出现异常"+e.getMessage());
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					throw new ExceptionUtil("下载出现异常"+ioe.getMessage());
				}
			}
		}
		return success;
	}
}
