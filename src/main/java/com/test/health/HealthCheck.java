/**
 * 
 */
package com.test.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @author MGupta
 *
 */
@Component
public class HealthCheck implements HealthIndicator {

	@Override
	public Health health() {
		int errorCode = check(); // TODO
		if (errorCode != 0) {
			return Health.down()
					.withDetail("Error Code", errorCode).build();
		}
		return Health.up().withDetail("It's up && running!!", 1).build();
	}

	public int check() {
		// Our logic to check health
		return 0;
	}

}
