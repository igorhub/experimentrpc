(defproject experimentrpc "dev"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/tools.cli "0.3.5"]
                 [com.taoensso/timbre "4.3.1"]
                 [me.raynes/fs "1.4.6"]
                 [ring "1.4.0"]
                 [slacker "0.13.3"]]
  :main experimentrpc.server)
