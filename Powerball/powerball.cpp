#include "powerball.hpp"

Powerball::Powerball(long long jackpot, int share_num, int one_cost) {	
	SetPrizes(jackpot/share_num);
	this->one_cost_ = one_cost;
}

void Powerball::SetPrizes(long long a, int b, int c, int d, int e, int f, int g, int h, int i, int j, int k) {
	all_prizes_.jackpot = a;
	all_prizes_.five_white = b;
	all_prizes_.four_white_one_red = c;
	all_prizes_.four_white = d;
	all_prizes_.three_white_one_red = e;
	all_prizes_.three_white = f;
	all_prizes_.two_white_one_red = g;
	all_prizes_.two_white = h; 
	all_prizes_.one_white_one_red = i;
	all_prizes_.one_white = j; 
	all_prizes_.one_red = k;
}

void Strategy::PrintNumbers() {
  if (recommend_numbers_ != NULL){
    RecommendNumbers::iterator it;
    for (it = recommend_numbers_->begin(); it != recommend_numbers_->end(); ++it){
      std::cout << (*it).red << ", " << (*it).first << " " << (*it).second << " "
                << (*it).third << " " << (*it).fourth << " " << (*it).fifth << std::endl;
    }
  }
};

