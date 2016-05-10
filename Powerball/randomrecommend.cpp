#include "randomrecommend.hpp"

RandomRecommend::RandomRecommend(int min, long long max) {
	min_money_ = min;
	max_money_ = max;
  srand (time(NULL));
}

void RandomRecommend::CalculateExpectation(Prizes prize) {
	double all_cases = 69.0 * 68 * 67 * 66 * 65 * 26;
	long long five_white = prize.jackpot + 25LL * prize.five_white;
	long long four_white = 5LL * 64 * (prize.four_white_one_red + 25 * prize.four_white);
	long long three_white = 10LL * 64 * 63 / 2 
                         * (prize.three_white_one_red + 25 * prize.three_white);
	long long two_white = 10LL * 64 * 63 * 62 / 3 / 2  
                       * (prize.two_white_one_red + 25 * prize.two_white);
	long long one_white = 5LL * 64 * 63 * 62 * 61 / 4 / 3 / 2 
                       * (prize.one_white_one_red + 25 * prize.one_white);
	long long one_red = 64LL * 63 * 62 * 61 * 60 * prize.one_red;
	single_expect_return_ = (five_white + four_white + three_white + two_white + one_white + one_red)
                            /  all_cases;
}


Number RandomRecommend::GetNumber(int red) {
  int whites[5];
  int k = 0;
  while (k < 5){
    int tmp = rand() % 69 + 1;
    bool is_dup = false;
    for (int j = 0; j < k; j++) {
      if (whites[j] == tmp) {
        is_dup = true;
        break;
      }
    }
    if (is_dup) {
      continue;
    } else {
      whites[k] = tmp;
      ++k;
    }
  }
  std::sort(whites, whites + 5);
  Number num = {red, whites[0], whites[1], whites[2], whites[3], whites[4]};
  return num;
}

//finds duplicated sets on a sorted vector, replace it with a new random set, 
//till there is no duplicated numbers.
//In order to make this run faster, I have tried to implement it partially on the same red number
//which should have less overhead of sorting, however no big difference.
//Maybe the duplicated number should be repleced with a number close to it, 
//and sort in its neighborhood
void RandomRecommend::ReplaceDup() {
  RecommendNumbers::iterator it;
  bool has_dup = true;
  while (has_dup) {
    has_dup = false;
    for (it = recommend_numbers_->begin() + 1; it != recommend_numbers_->end(); ++it){
      if ((*it) == (*(it - 1))){
        (*it) = GetNumber((*(it - 1)).red);
        has_dup = true;
      }
    }
    if (has_dup) 
      std::sort(recommend_numbers_->begin(), recommend_numbers_->end());
  } 
}

void RandomRecommend::GetRandomNumbers(long long total){
  recommend_numbers_ = new RecommendNumbers;
  int ticket_num_every_red = total / 26;
  int ticket_num_rest = total % 26;

  for (int i = 0; i < 26; ++i)
    for (int j = 0; j < ticket_num_every_red; ++j)
      recommend_numbers_->push_back(GetNumber(i + 1));

  int reds[26];
  for (int i = 0; i < 26; i++)
    reds[i] = i + 1;
  
  //randomly gets unique red numbers for the rest of sets
  for (int j = 0; j < ticket_num_rest; ++j) {
    int pos = rand() % (26 - j) + j;
    std::swap(reds[pos], reds[j]);
    recommend_numbers_->push_back(GetNumber(reds[j]));
  }
  std::sort(recommend_numbers_->begin(), recommend_numbers_->end());
  ReplaceDup();
}

void RandomRecommend::RecommendPowerball(const Powerball* ball) {
	int one_cost = ball->GetOneCost();
	Prizes all_prizes = ball->GetPrizes();
	CalculateExpectation(all_prizes);
  

	if (single_expect_return_ <= one_cost) {
		std::cout << "The chance of winning a Powerball is always very low,"
              << " the current jackpot doesn't worth you take the risk" << std::endl;
		if (min_money_ >= one_cost){
			std::cout << "Based on the minimum amount of money you want to spend,"
                << " here are some numbers you can consider:" << std::endl;
      GetRandomNumbers(min_money_ / one_cost);
		}		
	} else {
    std::cout << "The expect return outweighs the investment, it may be your lucky day!"
           << std::endl;
    if (max_money_ >= 69LL * 68 * 67 * 66 * 65 * 26 * one_cost){
      std::cout << "We suggest you buy every possible combination once" << std::endl;
    } else {
      std::cout << "Based on the maximum amount of money you want to spend,"
                << " here are some numbers you can consider:" << std::endl;
      GetRandomNumbers(max_money_ / one_cost);
    }      		
	}
  PrintNumbers();
}

