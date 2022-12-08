(def shape-scorecard '{:X 1 :Y 2 :Z 3})

(defn outcome-score [opponent-choice player-choice]
  (case opponent-choice
    :A (player-choice '{:X 3 :Y 6 :Z 0})
    :B (player-choice '{:X 0 :Y 3 :Z 6})
    :C (player-choice '{:X 6 :Y 0 :Z 3})))

(defn round-to-keywords [round]
  (->> (clojure.string/split round #" ") (map keyword)))

(def input-data
  (let [raw-input (-> (slurp "src/day-2/input.txt") (clojure.string/split #"\n"))]
    (map round-to-keywords raw-input)))

(defn calculate-round-score [opponent-choice player-choice]
  (reduce + [(outcome-score opponent-choice player-choice) (get shape-scorecard player-choice)]))

(defn calculate-game-score []
  (->> (map #(calculate-round-score (first %) (last %)) input-data)
       (reduce +)))