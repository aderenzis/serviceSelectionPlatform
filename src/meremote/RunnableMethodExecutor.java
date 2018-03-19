package meremote;

import java.rmi.RemoteException;

import me.domain.IRemoteMethodExecutor;

public class RunnableMethodExecutor implements Runnable {

	private IRemoteMethodExecutor rme;
	private String methodName;

	public RunnableMethodExecutor(IRemoteMethodExecutor rme, String methodName) {
		this.rme=rme;
		this.methodName=methodName;
	}

	public void run() {
		try {
			rme.execute(this.methodName);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
