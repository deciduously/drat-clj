(ns drat.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]))

;; - EVENT HANDLERS

(rf/reg-event-db
 :initialize
 (fn [_ _]
   {:date (js/Date.)
    :txt (.getItem js/localStorage "txt")}))

(rf/reg-event-db
 :txt-change
 (fn [db [_ new-txt]]
   (assoc db :txt new-txt)))

;; - QUERY

(rf/reg-sub
 :date
 (fn [db _]
   (:date db)))

(rf/reg-sub
 :txt
 (fn [db _]
   (:txt db)))

;; - UTILITY

(defn button
  [title action]
  [:input {:type "button" :value title :onClick action}])

(defn save
  []
  (.setItem js/localStorage "txt" @(rf/subscribe[:txt]))
  )

;; - VIEW

(defn entry
  []
  [:div.entry
   [:input
    {:type "text" :value @(rf/subscribe [:txt]) :on-change #(rf/dispatch [:txt-change (-> % .-target .-value)])}]])

(defn ui
  []
  [:div
   [entry]
   [:h1 (-> @(rf/subscribe [:date])
            .toDateString)]
   @(rf/subscribe [:txt])
   [:br]
   (button "SAVE" save)])

;; - ENTRY

(when-let [element (.getElementById js/document "app")]
  (rf/dispatch-sync [:initialize])
  (reagent/render [ui] element))
