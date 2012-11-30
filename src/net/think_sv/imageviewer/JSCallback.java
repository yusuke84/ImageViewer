package net.think_sv.imageviewer;


public class JSCallback {

	public interface OnJSEventListner {

		void onJSEvent( int etype );
		
	}

	private OnJSEventListner mOnJSEventListner;

	public JSCallback( OnJSEventListner onJSEventListner ) {
		if( onJSEventListner == null ) { throw new NullPointerException( "'OnJSEventListner' is null." ); }

		this.mOnJSEventListner = onJSEventListner;
	}
	

	public void onLoaded(int e) {
		this.mOnJSEventListner.onJSEvent( e );
	}


	
}
