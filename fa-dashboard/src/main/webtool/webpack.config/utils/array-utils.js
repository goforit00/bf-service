module.exports = {
    toArray(input/*, ...args*/) {
        var output, args = Array.prototype.slice.call(arguments, 1);
        if (args.length) {
            //output = Array.of(input, ...args);
            output = Array.from(arguments);
        } else if (!Array.isArray(input)) {
            /**
             * Different input types to Array.from(input) get different results.
             * numbers, booleans, object literals and functions > []
             * strings > str.split("")
             * null, undefined > error
             * @type {Array}
             */
            if (typeof input === "string") {
                output = [input];
            } else if (input === null || input === undefined) {
                output = [];
            } else {
                output = Array.from(input);
                if (!output.length) {
                    output = [input];
                }
            }
        } else {
            output = input;
        }
        return output;
    }
};