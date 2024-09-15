// Type for location
export interface Location {
  latitude: string;
  address: string;
  city: string;
  country_id: number;
  locality_verbose: string;
  city_id: number;
  zipcode: string;
  longitude: string;
  locality: string;
}

// Type for user rating
export interface UserRating {
  rating_text: string;
  rating_color: string;
  votes: string;
  aggregate_rating: string;
}

// Type for event photo
export interface EventPhoto {
  photo_id: number;
  order: number;
  type: string;
  url: string;
  md5sum: string;
  uuid: number;
  thumb_url: string;
}

// Type for event
export interface Event {
  display_date: string;
  end_time: string;
  date_added: string;
  start_date: string;
  photos: EventPhoto[];
  share_url: string;
  description: string;
  title: string;
  display_time: string;
  book_link: string;
  restaurants: any[];
  disclaimer: string;
  friendly_start_date: string;
  is_end_time_set: number;
  event_id: number;
  end_date: string;
  event_category: number;
  friendly_end_date: string;
  is_active: number;
  start_time: string;
  is_valid: number;
  event_category_name: string;
}

export interface IRestaurant {
  has_online_delivery: number;
  photos_url: string;
  url: string;
  price_range: number;
  apikey: string;
  user_rating: UserRating;
  R: { res_id: number };
  name: string;
  cuisines: string;
  is_delivering_now: number;
  deeplink: string;
  menu_url: string;
  average_cost_for_two: number;
  book_url: string;
  switch_to_order_menu: number;
  offers: any[];
  has_table_booking: number;
  location: Location;
  featured_image: string;
  zomato_events: Event[];
  currency: string;
  id: string;
  thumb: string;
  establishment_types: any[];
  events_url: string;
  restaurantId: number;
  restaurantName: string;
  countryCode: number;
  city: string;
  address: string;
  locality: string;
  localityVerbose: string;
  longitude: number;
  latitude: number;
  averageCostForTwo: number;
  hasTableBooking: string;
  hasOnlineDelivery: string;
  isDelivering: string;
  switchToOrderMenu: string;
  priceRange: number;
  aggregateRating: number;
  votes: number;
}
