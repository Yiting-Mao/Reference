
#include "powerball.hpp"
#include "randomrecommend.hpp"


int main(int argc, char** argv) {
  if (argc != 5) {
  	std::cout << "Usage: ./powerball jackpot_amount num_of_people_sharing_jackpot"
              << "minimum_money_want_to_spend maximum_money_want_to_spend" << std::endl;
  	return -1;
  }  
	//TODO: add more argv checks here
	long long jackpot = atoll(argv[1]);
	int share_num = atoi(argv[2]);
	int min_money = atoi(argv[3]);
	long long max_money = atoll(argv[4]);
	Powerball *powerball = new Powerball(jackpot, share_num, 2);
  RandomRecommend *random_recommend = new RandomRecommend(min_money, max_money);
  powerball -> GetRecommendNumbers(random_recommend);
  delete powerball;
  delete random_recommend;  
  return 0;
}