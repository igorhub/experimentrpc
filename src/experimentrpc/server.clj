(ns experimentrpc.server
  (:require [me.raynes.fs :as fs]
            [ring.adapter.jetty :as jetty]
            [slacker.server :as server]
            [experimentrpc.dispatch]))


(def port 30002)
(def scripts {"erpc-ls" "ls"})
(def bin-dir (str (System/getenv "HOME") "/.local/bin"))


(defn spit-scripts []
  (doseq [[global-name local-name] scripts
          :let [executable (fs/file bin-dir global-name)]]
    (spit executable (str "#!/bin/bash
args=\"\"
for arg in $@
do
args+=\\\"$arg\\\"
done
"
                          "curl localhost:" port "/experimentrpc.dispatch/dispatch.clj --silent --max-time 3 -d \"[\\\"" local-name "\\\" \\\"`env`\\\" [$args]]\" | sed 's/\\\\n/\\n/g'"))
    (fs/chmod "+x" executable)))


(defn -main []
  (spit-scripts)
  (jetty/run-jetty (server/slacker-ring-app (the-ns 'experimentrpc.dispatch))
                   {:port port}))
