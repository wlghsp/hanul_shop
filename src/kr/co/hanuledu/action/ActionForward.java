package kr.co.hanuledu.action;

public class ActionForward {
	
		// view page
		private String path;
		
		// true → sendRedirect, false → forward
		boolean isRedirect;

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public boolean isRedirect() {
			return isRedirect;
		}

		public void setRedirect(boolean isRedirect) {
			this.isRedirect = isRedirect;
		}
		
}
