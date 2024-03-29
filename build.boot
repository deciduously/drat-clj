(set-env!
 :source-paths #{"src/cljs"}
 :resource-paths #{"resource"}

 :dependencies '[[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.9.946"]
                 [adzerk/boot-cljs "2.1.4" :scope "test" ]
                 [pandeiro/boot-http "0.8.3" :scope "test"]
                 [adzerk/boot-reload "0.5.2" :scope "test"]
                 [adzerk/boot-cljs-repl "0.3.3" :scope "test"]
                 [com.cemerick/piggieback "0.2.2" :scope "test"]
                 [org.clojure/tools.nrepl "0.2.12" :scope "test"]
                 [weasel "0.7.0" :scope "test"]
                 [binaryage/devtools "0.9.7" :scope "test"]
                 [powerlaces/boot-cljs-devtools "0.2.0" :scope "test"]
                 [reagent "0.8.0-alpha2"]
                 [re-frame "0.10.4"]])

(require '[adzerk.boot-cljs :refer [cljs]]
         '[pandeiro.boot-http :refer [serve]]
         '[adzerk.boot-reload :refer [reload]]
         '[adzerk.boot-cljs-repl :refer [cljs-repl-env start-repl]]
         '[powerlaces.boot-cljs-devtools :refer [cljs-devtools]])

(deftask dev
  "Launch Immediate Feedback Development Environment"
  []
  (comp
   (serve :dir "target")
   (watch)
   (reload)
   (cljs-devtools)
   (cljs-repl-env)
   (cljs)
   (target :dir #{"target"})))
