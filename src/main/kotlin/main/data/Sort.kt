package main.data

import org.springframework.data.domain.Sort

fun sortByScoreDesc(): Sort = Sort(Sort.Direction.DESC, "score")
