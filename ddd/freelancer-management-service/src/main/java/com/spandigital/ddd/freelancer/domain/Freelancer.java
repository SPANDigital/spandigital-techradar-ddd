package com.spandigital.ddd.freelancer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Freelancer {

    @Id
    private FreelancerId id;
    private Address homeAddress;
    private List<ProjectId> projectIds;
    private List<CommunicationChannel> communicationChannels;

    public Freelancer() {
        this.id = new FreelancerId();
    }

    public static Freelancer of(Freelancer that) {
        return new Freelancer(
                that.id,
                that.homeAddress,
                List.copyOf(that.projectIds),
                List.copyOf(that.communicationChannels)
        );
    }

    public boolean assignedTo(ProjectId thatProjectId) {

        for (ProjectId thisProjectId : this.projectIds) {
            if (thisProjectId.equals(thatProjectId)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasEmailAddresses() {
        return !this.getEmailAddresses().isEmpty();
    }

    public boolean is(FreelancerId thatId) {
        return this.id.equals(thatId);
    }

    public boolean is(Freelancer that) {
        return this.id.equals(that.id);
    }

    public List<EmailAddress> getEmailAddresses() {
        return this.communicationChannels
                .stream()
                .filter(communicationChannel -> communicationChannel.getType() == ContactType.EMAIL)
                .map(communicationChannel -> (EmailAddress) communicationChannel)
                .collect(Collectors.toList());
    }

    public boolean hasAny(Iterable<EmailAddress> emailAddresses) {

        final Set<EmailAddress> emailAddressSet = new HashSet<>();
        emailAddresses.forEach(emailAddressSet::add);

        return this.getEmailAddresses()
                .stream()
                .anyMatch(emailAddressSet::contains);
    }
}
