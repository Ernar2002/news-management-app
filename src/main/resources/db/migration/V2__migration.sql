-- Generate UUIDs for sources
DO $$
DECLARE
source1_id UUID := gen_random_uuid();
  source2_id UUID := gen_random_uuid();
  source3_id UUID := gen_random_uuid();
  source4_id UUID := gen_random_uuid();
  source5_id UUID := gen_random_uuid();
  source6_id UUID := gen_random_uuid();
  source7_id UUID := gen_random_uuid();
  source8_id UUID := gen_random_uuid();
  source9_id UUID := gen_random_uuid();
  source10_id UUID := gen_random_uuid();
  topic1_id UUID := gen_random_uuid();
  topic2_id UUID := gen_random_uuid();
  topic3_id UUID := gen_random_uuid();
  topic4_id UUID := gen_random_uuid();
  topic5_id UUID := gen_random_uuid();
  topic6_id UUID := gen_random_uuid();
  topic7_id UUID := gen_random_uuid();
  topic8_id UUID := gen_random_uuid();
  topic9_id UUID := gen_random_uuid();
  topic10_id UUID := gen_random_uuid();
BEGIN
  -- Insert migration for the sources table
INSERT INTO sources (id, created_at, updated_at, active, name, url)
VALUES
    (source1_id, current_timestamp, current_timestamp, 'ACTIVE', 'CNN', 'http://www.cnn.com'),
    (source2_id, current_timestamp, current_timestamp, 'ACTIVE', 'BBC', 'http://www.bbc.co.uk'),
    (source3_id, current_timestamp, current_timestamp, 'ACTIVE', 'The New York Times', 'https://www.nytimes.com'),
    (source4_id, current_timestamp, current_timestamp, 'ACTIVE', 'Reuters', 'https://www.reuters.com'),
    (source5_id, current_timestamp, current_timestamp, 'ACTIVE', 'The Guardian', 'https://www.theguardian.com'),
    (source6_id, current_timestamp, current_timestamp, 'ACTIVE', 'Fox News', 'https://www.foxnews.com'),
    (source7_id, current_timestamp, current_timestamp, 'ACTIVE', 'Al Jazeera', 'https://www.aljazeera.com'),
    (source8_id, current_timestamp, current_timestamp, 'ACTIVE', 'NBC News', 'https://www.nbcnews.com'),
    (source9_id, current_timestamp, current_timestamp, 'ACTIVE', 'The Washington Post', 'https://www.washingtonpost.com'),
    (source10_id, current_timestamp, current_timestamp, 'ACTIVE', 'The Times of India', 'https://timesofindia.indiatimes.com');

-- Insert migration for the topics table
INSERT INTO topics (id, created_at, updated_at, active, name)
VALUES
    (topic1_id, current_timestamp, current_timestamp, 'ACTIVE', 'Politics'),
    (topic2_id, current_timestamp, current_timestamp, 'ACTIVE', 'Technology'),
    (topic3_id, current_timestamp, current_timestamp, 'ACTIVE', 'Sports'),
    (topic4_id, current_timestamp, current_timestamp, 'ACTIVE', 'Entertainment'),
    (topic5_id, current_timestamp, current_timestamp, 'ACTIVE', 'Science'),
    (topic6_id, current_timestamp, current_timestamp, 'ACTIVE', 'Business'),
    (topic7_id, current_timestamp, current_timestamp, 'ACTIVE', 'Health'),
    (topic8_id, current_timestamp, current_timestamp, 'ACTIVE', 'Education'),
    (topic9_id, current_timestamp, current_timestamp, 'ACTIVE', 'Environment'),
    (topic10_id, current_timestamp, current_timestamp, 'ACTIVE', 'Travel');

-- Insert migration for the news table
INSERT INTO news (id, created_at, updated_at, active, title, content, source_id, topic_id)
VALUES
    (gen_random_uuid(), current_timestamp, current_timestamp, 'ACTIVE', 'Breaking News', 'This is the content of the breaking news article.', source1_id, topic1_id),
    (gen_random_uuid(), current_timestamp, current_timestamp, 'ACTIVE', 'Tech Trends', 'Discover the latest technology trends.', source2_id, topic2_id),
    (gen_random_uuid(), current_timestamp, current_timestamp, 'ACTIVE', 'Election Results', 'Get the updates on the election results.', source1_id, topic1_id),
    (gen_random_uuid(), current_timestamp, current_timestamp, 'ACTIVE', 'Sports Highlights', 'Check out the exciting sports highlights from the recent matches.', source3_id, topic3_id),
    (gen_random_uuid(), current_timestamp, current_timestamp, 'ACTIVE', 'Movie Review', 'Read our review of the latest blockbuster movie.', source4_id, topic4_id),
    (gen_random_uuid(), current_timestamp, current_timestamp, 'ACTIVE', 'Space Exploration', 'Learn about the latest space exploration missions.', source5_id, topic5_id),
    (gen_random_uuid(), current_timestamp, current_timestamp, 'ACTIVE', 'Business News', 'Get the latest business news.', source6_id, topic6_id),
    (gen_random_uuid(), current_timestamp, current_timestamp, 'ACTIVE', 'Health Tips', 'Learn about the latest health tips.', source7_id, topic7_id),
    (gen_random_uuid(), current_timestamp, current_timestamp, 'ACTIVE', 'Education News', 'Get the latest education news.', source8_id, topic8_id),
    (gen_random_uuid(), current_timestamp, current_timestamp, 'ACTIVE', 'Environment News', 'Get the latest environment news.', source9_id, topic9_id),
    (gen_random_uuid(), current_timestamp, current_timestamp, 'ACTIVE', 'Travel News', 'Get the latest travel news.', source10_id, topic10_id);
END $$;
