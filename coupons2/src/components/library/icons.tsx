import { library } from '@fortawesome/fontawesome-svg-core'
import { fab } from '@fortawesome/free-brands-svg-icons'
import { faSmile } from '@fortawesome/free-regular-svg-icons'
import { faCheckSquare, faCoffee, faBatteryFull } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

library.add(fab, faCheckSquare, faCoffee, faBatteryFull, faSmile);

export const showCase = () => (
    <div>
        <FontAwesomeIcon icon="check-square" />
        your <FontAwesomeIcon icon="coffee" /> is hot and ready!
        <FontAwesomeIcon icon="battery-full" />
    </div>
);

export const Showcase = () => (
    <div>
        <FontAwesomeIcon icon={["fab", "youtube"]} />
        <FontAwesomeIcon icon={["fab", "facebook"]} />
        <FontAwesomeIcon icon={["fab", "twitter"]} />
        <FontAwesomeIcon icon={["fab", "mailbox"]} />
        <FontAwesomeIcon icon={["fas", "battery-full"]} />
    </div>
);