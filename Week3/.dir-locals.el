;;; Directory Local Variables
;;; For more information see (info "(emacs) Directory Variables")

((nil
  (eval setenv "CLASSPATH"
	(concat
	 (expand-file-name "~/.m2/repository/edu/princeton/cs/algs4/algs4/1.0.0/algs4-1.0.0.jar:")
	 (let
	     ((l
	       (dir-locals-find-file
		(or
		 (buffer-file-name)
		 default-directory))))
	   (if
	       (listp l)
	       (car l)
	     l))
	 "target/classes"))))
